package com.enderio.base.common.integrations.jei;

import com.enderio.EnderIO;
import com.enderio.base.common.init.EIOItems;
import com.enderio.base.common.integrations.jei.category.FireCraftingCategory;
import com.enderio.base.common.integrations.jei.extension.ShapedEntityStorageCategoryExtension;
import com.enderio.base.common.integrations.jei.subtype.EntityStorageSubtypeInterpreter;
import com.enderio.base.common.recipe.ShapedEntityStorageRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class EnderIOJEI implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return EnderIO.loc("base");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new FireCraftingCategory(guiHelper));
    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
        registration.getCraftingCategory().addCategoryExtension(ShapedEntityStorageRecipe.class, r -> ShapedEntityStorageRecipe.REGISTERED_RECIPES.contains(r.getId()), ShapedEntityStorageCategoryExtension::new);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        EnderIOJEIRecipes recipes = new EnderIOJEIRecipes();
        registration.addRecipes(FireCraftingCategory.TYPE, recipes.getAllFireCraftingRecipes());
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(EIOItems.FILLED_SOUL_VIAL.get(), new EntityStorageSubtypeInterpreter());
        registration.registerSubtypeInterpreter(EIOItems.BROKEN_SPAWNER.get(), new EntityStorageSubtypeInterpreter());
    }
}