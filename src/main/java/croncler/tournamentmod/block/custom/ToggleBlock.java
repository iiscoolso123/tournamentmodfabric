package croncler.tournamentmod.block.custom;


import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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
        world.setBlockState(pos, state.with(CHARGED, world.getBlockState(sourcePos).get(CHARGED)));
    }



    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockState(pos).get(CHARGED)){
            world.setBlockState(pos, state.with(CHARGED, false));

        }else {
            world.setBlockState(pos, state.with(CHARGED, true));
        }

        return ActionResult.SUCCESS;
    }
}
