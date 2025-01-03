package com.kekecreations.jinxedlib.core.registry;

import net.minecraft.world.item.DyeColor;

import java.util.ArrayList;

public class JinxedCreativeCategoryUtils {

    public static ArrayList<DyeColor> colourOrderBackToFront = new ArrayList<DyeColor>();
    public static ArrayList<DyeColor> colourOrder = new ArrayList<DyeColor>();

    /**
     * This method allows you to add a custom dye to the colour order in the creative category (after adding a custom dye to this list the blocks should appear in the creative category)
     *
     * @param  color a vanilla or modded dye
     * @param  index where it places the colour in the list
     */
    public static void addDyeToColourOrderBackToFront(DyeColor color, int index) {
        colourOrderBackToFront.add(index, color);
    }

    /**
     * This method allows you to add a custom dye to the colour order in the creative category (after adding a custom dye to this list the blocks should appear in the creative category)
     *
     * @param  color a vanilla or modded dye
     * @param  index where it places the colour in the list
     */
    public static void addDyeToColourOrder(DyeColor color, int index) {
        colourOrder.add(index, color);
    }

    //ORDER IS BACK TO FRONT FOR, FOR LOOPS THAT USE THE AFTER ITEM METHOD
    public static void addVanillaDyesToColourOrderBackToFront() {
        colourOrderBackToFront.add(DyeColor.PINK);
        colourOrderBackToFront.add(DyeColor.MAGENTA);
        colourOrderBackToFront.add(DyeColor.PURPLE);
        colourOrderBackToFront.add(DyeColor.BLUE);
        colourOrderBackToFront.add(DyeColor.LIGHT_BLUE);
        colourOrderBackToFront.add(DyeColor.CYAN);
        colourOrderBackToFront.add(DyeColor.GREEN);
        colourOrderBackToFront.add(DyeColor.LIME);
        colourOrderBackToFront.add(DyeColor.YELLOW);
        colourOrderBackToFront.add(DyeColor.ORANGE);
        colourOrderBackToFront.add(DyeColor.RED);
        colourOrderBackToFront.add(DyeColor.BROWN);
        colourOrderBackToFront.add(DyeColor.BLACK);
        colourOrderBackToFront.add(DyeColor.GRAY);
        colourOrderBackToFront.add(DyeColor.LIGHT_GRAY);
        colourOrderBackToFront.add(DyeColor.WHITE);
    }

    //ORDER IS RIGHT FOR OTHER METHODS
    public static void addVanillaDyesToColourOrder() {
        colourOrder.add(DyeColor.WHITE);
        colourOrder.add(DyeColor.LIGHT_GRAY);
        colourOrder.add(DyeColor.GRAY);
        colourOrder.add(DyeColor.BLACK);
        colourOrder.add(DyeColor.BROWN);
        colourOrder.add(DyeColor.RED);
        colourOrder.add(DyeColor.ORANGE);
        colourOrder.add(DyeColor.YELLOW);
        colourOrder.add(DyeColor.LIME);
        colourOrder.add(DyeColor.GREEN);
        colourOrder.add(DyeColor.CYAN);
        colourOrder.add(DyeColor.LIGHT_BLUE);
        colourOrder.add(DyeColor.BLUE);
        colourOrder.add(DyeColor.PURPLE);
        colourOrder.add(DyeColor.MAGENTA);
        colourOrder.add(DyeColor.PINK);
    }
}
