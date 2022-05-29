package net.wiremc;

import org.bukkit.plugin.Plugin;

/**
 * this doc was created on 29.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 */

public class CoreModule extends net.wiremc.common.api.common.modules.CoreModule {

    public void onHandleEnable(Plugin plugin) {
        System.out.println(" ");
        System.out.println(plugin.getServer().getName());
        System.out.println(" ");
    }

}
