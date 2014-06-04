package net.minecraft.server;

import java.util.Random;

public class BlockIce extends BlockHalfTransparent {

    public BlockIce() {
        super("ice", Material.ICE, false);
        this.frictionFactor = 0.98F;
        this.a(true);
        this.a(CreativeModeTab.b);
    }

    public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l) {
        entityhuman.a(StatisticList.MINE_BLOCK_COUNT[Block.getId(this)], 1);
        entityhuman.applyExhaustion(0.025F);
        if (this.E() && EnchantmentManager.hasSilkTouchEnchantment(entityhuman)) {
            ItemStack itemstack = this.j(l);

            if (itemstack != null) {
                this.a(world, i, j, k, itemstack);
            }
        } else {
            if (world.worldProvider.f) {
                world.setAir(i, j, k);
                return;
            }

            int i1 = EnchantmentManager.getBonusBlockLootEnchantmentLevel(entityhuman);

            this.b(world, i, j, k, l, i1);
            Material material = world.getType(i, j - 1, k).getMaterial();

            if (material.isSolid() || material.isLiquid()) {
                world.setTypeUpdate(i, j, k, Blocks.WATER);
            }
        }
    }

    public int a(Random random) {
        return 0;
    }

    public void a(World world, int i, int j, int k, Random random) {
        if (world.b(EnumSkyBlock.BLOCK, i, j, k) > 11 - this.k()) {
            if (world.worldProvider.f) {
                world.setAir(i, j, k);
                return;
            }

            this.b(world, i, j, k, world.getData(i, j, k), 0);
            world.setTypeUpdate(i, j, k, Blocks.STATIONARY_WATER);
        }
    }

    public int h() {
        return 0;
    }
}
