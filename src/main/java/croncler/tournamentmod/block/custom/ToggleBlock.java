package croncler.tournamentmod.block.custom;


import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ToggleBlock extends Block {
    private static final VoxelShape FALLING_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 0.0, 0.0f, 0.0);
    private static final VoxelShape NORMAL = VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1.0f, 1f);
    public static final BooleanProperty CHARGED = BooleanProperty.of("charged");

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
    }

    public ToggleBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(CHARGED, false));
    }
    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        if (state.get(CHARGED).booleanValue()) {
            return VoxelShapes.empty();
        }
        return NORMAL;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(CHARGED).booleanValue()) {
            return VoxelShapes.empty();
        }
        return NORMAL;
    }



    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        if (world.isReceivingRedstonePower(pos) && !(sourceBlock instanceof ToggleBlock)) {
            world.setBlockState(pos, state.with(CHARGED, true));
        }else if (!(sourceBlock instanceof ToggleBlock)){
            world.setBlockState(pos, state.with(CHARGED, false));
        } else
        if (sourceBlock instanceof ToggleBlock) {
            if (world.getBlockState(pos).get(CHARGED) != world.getBlockState(sourcePos).get(CHARGED)) {
                world.scheduleBlockTick(new BlockPos(pos), this, (int) 1);
            }
        }
    }

    /// @Override
   // public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
     ///   if (!world.isClient) {
       //     super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
   //         if (world.isReceivingRedstonePower(pos) && !(sourceBlock instanceof ToggleBlock)) {
    //            world.setBlockState(pos, state.with(CHARGED, true));
      //      } else {
      //          if (sourceBlock instanceof ToggleBlock) {
      //              if (world.getBlockState(pos).get(CHARGED) != world.getBlockState(sourcePos).get(CHARGED)) {
      //                  world.scheduleBlockTick(new BlockPos(pos), this, (int) 1);
      //              }
     //           }else {
   //                 world.scheduleBlockTick(new BlockPos(pos), this, (int) 1);
    //            }
   ///         }
   //     }
 //   }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        world.setBlockState(pos, state.with(CHARGED, !world.getBlockState(pos).get(CHARGED)));
        world.playSound(null,pos,SoundEvents.ENTITY_ITEM_PICKUP,SoundCategory.BLOCKS,0.04f,0.7f);
    }
}
