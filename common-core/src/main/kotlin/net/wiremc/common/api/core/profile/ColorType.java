package net.wiremc.common.api.core.profile;

import org.bukkit.Color;

/**
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 */

public enum ColorType {

    BLUE("§9", "http://textures.minecraft.net/texture/3f3e406291174d24cdf0f953f8a174a82bb3489dce8f679a443ef1aae0169061", "https://cdn.discordapp.com/attachments/836969872839540806/963760471989702716/blue.png", Color.BLUE),

    LIGHT_BLUE("§b", "http://textures.minecraft.net/texture/163e6646f1c0d41fd3bf5584a1ce044f5c46d598258db46216117859f57af197", "https://cdn.discordapp.com/attachments/836969872839540806/963760471989702716/blue.png", Color.AQUA),

    RED("§4", "http://textures.minecraft.net/texture/86d35a963d5987894b6bc214e328b39cd2382426ff9c8e082b0b6a6e044d3a3", "https://cdn.discordapp.com/attachments/836969872839540806/963760472899870751/red.png", Color.RED),

    LIGHT_RED("§c", "http://textures.minecraft.net/texture/6953b12a0946b629b4c0889d41fd26ed26fb729d4d514b59727124c37bb70d8d", "https://cdn.discordapp.com/attachments/836969872839540806/963760472899870751/red.png", Color.RED),

    GREEN("§2", "http://textures.minecraft.net/texture/53581c2f9cf358d7edc78dd6fd4b6257501bc4e6455e33fa0caae207cf0321a2", "https://cdn.discordapp.com/attachments/836969872839540806/963760472287494195/green.png", Color.GREEN),

    LIGHT_GREEN("§a", "http://textures.minecraft.net/texture/d67470a0c18f6851e914353719e795877d29b3252f7e6bd4a1b865765bd74feb", "https://cdn.discordapp.com/attachments/836969872839540806/963760472287494195/green.png", Color.GREEN),

    YELLOW("§e", "http://textures.minecraft.net/texture/4d905269accab24b11924eba8bd92991b8d85ce4276027a1636c931b6d06c89e", "https://cdn.discordapp.com/attachments/836969872839540806/963760473122160680/yellow.png", Color.YELLOW),

    ORANGE("§6", "http://textures.minecraft.net/texture/adf2eb205a23c1196b3ecf21e68c076b696e76163ac8fc4fb9f5318c2a5e5b1a", "https://cdn.discordapp.com/attachments/836969872839540806/963760473122160680/yellow.png", Color.ORANGE),

    PINK("§d", "http://textures.minecraft.net/texture/d0883650ea929db0eabdf5bc75599d8ef00d70340cd1ce5e04ad95ef8ed83b73", "https://cdn.discordapp.com/attachments/836969872839540806/963760472690139207/pink.png", Color.PURPLE),

    BLACK("§0", "http://textures.minecraft.net/texture/cfa4dda6d19a1fe2d988d65dec53429505308166c9067b68a4770ca5c436cf94", "https://cdn.discordapp.com/attachments/836969872839540806/963760472488837150/grey.png", Color.BLACK),

    WHITE("§r", "http://textures.minecraft.net/texture/3faf4c29f1e7405f4680c5c2b03ef9384f1aecfe2986ad50138c605fefff2f15", "https://cdn.discordapp.com/attachments/836969872839540806/963760472488837150/grey.png", Color.WHITE),

    PURPLE("§5", "http://textures.minecraft.net/texture/89ec5a30222d0659b0dbee844b8f53eae62fe95b4a3448a9ef790a7aedb296d9", "https://cdn.discordapp.com/attachments/836969872839540806/963760472690139207/pink.png", Color.PURPLE),

    GRAY("§7", "http://textures.minecraft.net/texture/55288ddc911a75f77c3a5d336365a8f8b139fa53930b4b6ee139875c80ce366c", "https://cdn.discordapp.com/attachments/836969872839540806/963760472488837150/grey.png", Color.GRAY),

    AQUA("§3", "http://textures.minecraft.net/texture/1fadf741ab76cd3620ad161300202d7b59a33051e5967e4b6194bac40bb280ff", "https://cdn.discordapp.com/attachments/836969872839540806/963760471989702716/blue.png", Color.AQUA);

    final String color;

    private final String url;
    private final String banner;
    private final Color bukkitColor;

    ColorType(String color, String url, String banner, Color bukkitColor) {
        this.color = color;
        this.url = url;
        this.banner = banner;

        this.bukkitColor = bukkitColor;
    }

    public Color toColor() {
        return bukkitColor;
    }

    public static ColorType getBy(String url) {
        for (ColorType values : values()) {
            if (values.url.equals(url)) return values;
        } return null;
    }

    public String getBanner() {
        return banner;
    }

    public static ColorType get(String colorCode) {
        byte var3 = -1;
        switch(colorCode.hashCode()) {
            case 5225:
                if (colorCode.equals("§0")) {
                    var3 = 0;
                }
                break;
            case 5227:
                if (colorCode.equals("§2")) {
                    var3 = 4;
                }
                break;
            case 5229:
                if (colorCode.equals("§4")) {
                    var3 = 1;
                }
                break;
            case 5231:
                if (colorCode.equals("§6")) {
                    var3 = 3;
                }
                break;
            case 5234:
                if (colorCode.equals("§9")) {
                    var3 = 2;
                }
                break;
            case 5274:
                if (colorCode.equals("§a")) {
                    var3 = 6;
                }
                break;
            case 5275:
                if (colorCode.equals("§b")) {
                    var3 = 5;
                }
                break;
            case 5276:
                if (colorCode.equals("§c")) {
                    var3 = 7;
                }
                break;
            case 5277:
                if (colorCode.equals("§d")) {
                    var3 = 8;
                }
                break;
            case 5278:
                if (colorCode.equals("§e")) {
                    var3 = 10;
                }
                break;
            case 5279:
                if (colorCode.equals("§f")) {
                    var3 = 9;
                }
        }



        switch (var3) {
            case 0 : return ColorType.BLACK;
            case 1 : return ColorType.RED;
            case 2 : return ColorType.BLUE;
            case 3 : return ColorType.ORANGE;
            case 4 : return ColorType.GREEN;
            case 5 : return ColorType.LIGHT_BLUE;
            case 6 : return ColorType.LIGHT_GREEN;
            case 7 : return ColorType.LIGHT_RED;
            case 8 : return ColorType.PINK;
            case 9 : return ColorType.WHITE;
            case 10 : return ColorType.YELLOW;
        };
        return null;
    }

    @Override
    public String toString() {
        return this.color;
    }

    public String getUrl() {
        return url;
    }

    public String getColor() {
        return this.color;
    }
}
