package ui.theme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public final class DefaultTheme {

    private DefaultTheme() {}

    public static final Dimension INPUT_MAX_SIZE = new Dimension(Integer.MAX_VALUE, 46);

    // Couleurs, triées par type
    public static final Color COLOR_PRIMARY        = new Color(13, 110, 253);
    public static final Color COLOR_ON_PRIMARY     = Color.WHITE;

    public static final Color COLOR_TEXT_PRIMARY   = new Color(33, 37, 41);
    public static final Color COLOR_TEXT_SECONDARY = new Color(73, 80, 87);
    public static final Color COLOR_TEXT_HINT      = new Color(173, 181, 189);
    public static final Color COLOR_TEXT_MUTED     = new Color(108, 117, 125);
    public static final Color COLOR_TEXT_INVERSE   = new Color(248, 249, 250);
    
    public static final Color COLOR_BG_PAGE        = new Color(248, 249, 250);
    public static final Color COLOR_BG_SURFACE     = Color.WHITE;
    public static final Color COLOR_BG_INVERSE     = new Color(33, 37, 41);
    public static final Color COLOR_BG_INPUT       = new Color(251, 252, 253);
    
    public static final Color COLOR_BORDER         = new Color(206, 212, 218);
    public static final Color COLOR_BORDER_SUBTLE  = new Color(226, 232, 240);
    public static final Color COLOR_BORDER_BUTTON  = new Color(222, 226, 230);

    // Polices d'écriture, triées par taille
    private static final String FONT_FAMILY = "Segoe UI";

    /* 

    public static final Font FONT_TITLE_L = new Font(FONT_FAMILY, Font.BOLD, 22);
    public static final Font FONT_TITLE_M = new Font(FONT_FAMILY, Font.BOLD, 18);
    public static final Font FONT_TITLE_S = new Font(FONT_FAMILY, Font.BOLD, 16);
    */

    public static final Font FONT_DISPLAY    = new Font(FONT_FAMILY, Font.BOLD, 30);
    public static final Font FONT_TITLE_XL   = new Font(FONT_FAMILY, Font.BOLD, 26);
    public static final Font FONT_TITLE_L    = new Font(FONT_FAMILY, Font.BOLD, 24);
    public static final Font FONT_TITLE      = new Font(FONT_FAMILY, Font.BOLD, 22);
    public static final Font FONT_CARD_LABEL = new Font(FONT_FAMILY, Font.BOLD, 20);
    public static final Font FONT_TITLE_S    = new Font(FONT_FAMILY, Font.BOLD, 18);
    public static final Font FONT_TITLE_XS   = new Font(FONT_FAMILY, Font.BOLD, 16);
    public static final Font FONT_BUTTON     = new Font(FONT_FAMILY, Font.BOLD, 14);
    public static final Font FONT_BOLD       = new Font(FONT_FAMILY, Font.BOLD, 12);
    public static final Font FONT_LABEL      = new Font(FONT_FAMILY, Font.PLAIN, 15);
    public static final Font FONT_LABEL_S    = new Font(FONT_FAMILY, Font.PLAIN, 13);
    public static final Font FONT_BODY       = new Font(FONT_FAMILY, Font.PLAIN, 14);
    public static final Font FONT_BODY_S     = new Font(FONT_FAMILY, Font.PLAIN, 12);
    public static final Font FONT_BODY_XS    = new Font(FONT_FAMILY, Font.PLAIN, 11);
    
    private static final String FONT_FAMILY_ICON   = "Segoe UI Emoji";
    public static final Font FONT_ICON_XL   = new Font(FONT_FAMILY_ICON, Font.PLAIN, 36);
    public static final Font FONT_ICON_L    = new Font(FONT_FAMILY_ICON, Font.PLAIN, 32);
    public static final Font FONT_ICON      = new Font(FONT_FAMILY_ICON, Font.PLAIN, 28);
    public static final Font FONT_ICON_S    = new Font(FONT_FAMILY_ICON, Font.PLAIN, 14);

    private static final String FONT_FAMILY_SYMBOL = "Segoe UI Symbol";
    public static final Font FONT_SYMBOL_XL = new Font(FONT_FAMILY_SYMBOL, Font.BOLD, 50);
    public static final Font FONT_SYMBOL_L  = new Font(FONT_FAMILY_SYMBOL, Font.BOLD, 40);
    public static final Font FONT_SYMBOL    = new Font(FONT_FAMILY_SYMBOL, Font.BOLD, 30);
    public static final Font FONT_SYMBOL_S  = new Font(FONT_FAMILY_SYMBOL, Font.BOLD, 20);
}