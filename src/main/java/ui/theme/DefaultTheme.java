package ui.theme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public final class DefaultTheme {
    
    private DefaultTheme() {}
    
    public static final Dimension INPUT_MAX_SIZE       = new Dimension(Integer.MAX_VALUE, 46);
    
    //TODO Utiliser des noms plus intuitifs
    // Couleurs, triées par nom
    public static final Color FOREGROUND_HERO_SUBTITLE = new Color(222, 226, 230);
    public static final Color FOREGROUND_HERO_HINT     = new Color(173, 181, 189);
    public static final Color FOREGROUND_MUTED         = new Color(108, 117, 125);
    public static final Color BACKGROUND_HERO          = new Color(33, 37, 41);
    public static final Color BACKGROUND_INPUT         = new Color(251, 252, 253);
    public static final Color BACKGROUND_COLOR         = new Color(248, 249, 250);
    public static final Color BACKGROUND_CARD          = Color.WHITE;
    public static final Color BORDER_BUTTON            = new Color(222, 226, 230);
    public static final Color BORDER_INPUT             = new Color(206, 212, 218);
    public static final Color BORDER_SOFT              = new Color(226, 232, 240);
    public static final Color TEXT_COLOR               = new Color(33, 37, 41);
    public static final Color SUBTEXT_COLOR            = new Color(108, 117, 125);
    public static final Color BUTTON_TEXT_COLOR        = Color.WHITE;
    public static final Color BUTTON_COLOR             = new Color(13, 110, 253);
    public static final Color LABEL_COLOR              = new Color(73, 80, 87);
    public static final Color FIELD_BORDER_COLOR       = new Color(206, 212, 218);
    public static final Color FIELD_BACKGROUND_COLOR   = new Color(251, 252, 253);

    // Polices d'écriture, triées par taille
    private static final String FONT_NAME        = "Segoe UI";
    private static final String ICON_FONT_NAME   = "Segoe UI Emoji";
    private static final String SYMBOL_FONT_NAME = "Segoe UI Symbol";
    public static final Font FONT_ICON_BIG   = new Font(DefaultTheme.ICON_FONT_NAME, Font.PLAIN, 36);
    public static final Font FONT_ICON       = new Font(DefaultTheme.ICON_FONT_NAME, Font.PLAIN, 32);
    public static final Font FONT_ICON_ALT   = new Font(DefaultTheme.ICON_FONT_NAME, Font.PLAIN, 28);
    public static final Font FONT_ICON_SMALL = new Font(DefaultTheme.ICON_FONT_NAME, Font.PLAIN, 14);
    public static final Font FONT_SYMBOL_BIG = new Font(DefaultTheme.SYMBOL_FONT_NAME, Font.BOLD, 50);
    public static final Font FONT_SYMBOL     = new Font(DefaultTheme.SYMBOL_FONT_NAME, Font.BOLD, 30);
    public static final Font FONT_TITLE_BIG  = new Font(DefaultTheme.FONT_NAME, Font.BOLD, 30);
    public static final Font FONT_TITLE      = new Font(DefaultTheme.FONT_NAME, Font.BOLD, 26);
    public static final Font FONT_TITLE_2    = new Font(DefaultTheme.FONT_NAME, Font.BOLD, 25);
    public static final Font FONT_TITLE_LABEL= new Font(DefaultTheme.FONT_NAME, Font.BOLD, 24);
    public static final Font FONT_HERO_TITLE = new Font(DefaultTheme.FONT_NAME, Font.BOLD, 22);
    public static final Font FONT_CARD_LABEL = new Font(DefaultTheme.FONT_NAME, Font.BOLD, 20);
    public static final Font FONT_TITLE_ALT  = new Font(DefaultTheme.FONT_NAME, Font.BOLD, 18);
    public static final Font FONT_TITLE_SMALL= new Font(DefaultTheme.FONT_NAME, Font.BOLD, 16);
    public static final Font FONT_BUTTON_2   = new Font(DefaultTheme.FONT_NAME, Font.BOLD, 15);
    public static final Font FONT_BUTTON     = new Font(DefaultTheme.FONT_NAME, Font.BOLD, 14);
    public static final Font FONT_BUTTON_ALT = new Font(DefaultTheme.FONT_NAME, Font.BOLD, 13);
    public static final Font FONT_BOLD       = new Font(DefaultTheme.FONT_NAME, Font.BOLD, 12);
    public static final Font FONT_LABEL_BIG  = new Font(DefaultTheme.FONT_NAME, Font.PLAIN, 16);
    public static final Font FONT_LABEL      = new Font(DefaultTheme.FONT_NAME, Font.PLAIN, 15);
    public static final Font FONT_BODY       = new Font(DefaultTheme.FONT_NAME, Font.PLAIN, 14);
    public static final Font FONT_FIELD      = new Font(DefaultTheme.FONT_NAME, Font.PLAIN, 14);
    public static final Font FONT_LABEL_SMALL= new Font(DefaultTheme.FONT_NAME, Font.PLAIN, 13);
    public static final Font FONT_HERO_HINT  = new Font(DefaultTheme.FONT_NAME, Font.PLAIN, 12);
    public static final Font FONT_BODY_SMALL = new Font(DefaultTheme.FONT_NAME, Font.PLAIN, 11);
    



}
