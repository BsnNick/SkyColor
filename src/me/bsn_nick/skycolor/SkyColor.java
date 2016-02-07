package me.bsn_nick.skycolor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author bsn_nick (000Nick)
 */
public class SkyColor extends JavaPlugin implements Listener
{
	private double _version = 1.0;
	
	public void onEnable()
	{
		Bukkit.getPluginManager().registerEvents(this, this);
		
		System.out.println("[SkyColor] SkyColor v" + _version + " by bsn_nick has been enabled!");
	}
	
	public void onDisable()
	{
		System.out.println("[SkyColor] SkyColor v" + _version + " by bsn_nick has been disabled!");
	}
	
    private Class<?> getNMSClass(String nmsClassString) throws ClassNotFoundException
    {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String name = "net.minecraft.server." + version + nmsClassString;
        Class<?> nmsClass = Class.forName(name);
        
        return nmsClass;
    }
    
    private Object getConnection(Player player) throws SecurityException, NoSuchMethodException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        Method getHandle = player.getClass().getMethod("getHandle");
        Object nmsPlayer = getHandle.invoke(player);
        Field conField = nmsPlayer.getClass().getField("playerConnection");
        Object con = conField.get(nmsPlayer);
        return con;
    }
	
	private void sendPacket(Player player, int number)
	{
		try
		{
			Class<?> packetClass = this.getNMSClass("PacketPlayOutGameStateChange");
	        Constructor<?> packetConstructor = packetClass.getConstructor(int.class, float.class);
	        Object packet = packetConstructor.newInstance(7, number);
	        Method sendPacket = getNMSClass("PlayerConnection").getMethod("sendPacket", this.getNMSClass("Packet"));
	        sendPacket.invoke(this.getConnection(player), packet);
		}
		catch (Exception e)
		{
			System.out.println("[SkyColor] Error: The packet could not be sent! [1]");
		}
	}
	
	@EventHandler
	public void command(PlayerCommandPreprocessEvent event)
	{
		if (event.getPlayer().hasPermission("skycolor.cmd"))
		{
			if ((event.getMessage().equalsIgnoreCase("/skycolor")) || (event.getMessage().equalsIgnoreCase("/skycolorall")))
			{
				event.setCancelled(true);
				
				event.getPlayer().sendMessage("");
				event.getPlayer().sendMessage(ChatColor.RED + "SkyColor> " + ChatColor.BLUE + "Use /skycolor <number>, and /skycolorall <number>");
				event.getPlayer().sendMessage(ChatColor.RED + "SkyColor> " + ChatColor.BLUE + "0: Clear sky");
				event.getPlayer().sendMessage(ChatColor.RED + "SkyColor> " + ChatColor.BLUE + "1: Normal rain storm");
				event.getPlayer().sendMessage(ChatColor.RED + "SkyColor> " + ChatColor.BLUE + "2: Brown sky + rain");
				event.getPlayer().sendMessage(ChatColor.RED + "SkyColor> " + ChatColor.BLUE + "3: Red sky + rain");
				event.getPlayer().sendMessage(ChatColor.RED + "SkyColor> " + ChatColor.BLUE + "4: Darker red sky + rain");
				event.getPlayer().sendMessage(ChatColor.RED + "SkyColor> " + ChatColor.BLUE + "5: Black/red sky + rain + glitchy tints");
				event.getPlayer().sendMessage(ChatColor.RED + "SkyColor> " + ChatColor.BLUE + "6: Multicolored sky + rain + multicolored tints");
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolor 0"))
			{
				event.setCancelled(true);
				
				sendPacket(event.getPlayer(), 0);
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolor 1"))
			{
				event.setCancelled(true);
				
				sendPacket(event.getPlayer(), 1);
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolor 2"))
			{
				event.setCancelled(true);
				
				sendPacket(event.getPlayer(), 2);
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolor 3"))
			{
				event.setCancelled(true);
				
				sendPacket(event.getPlayer(), 3);
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolor 4"))
			{
				event.setCancelled(true);
				
				sendPacket(event.getPlayer(), 4);
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolor 5"))
			{
				event.setCancelled(true);
				
				sendPacket(event.getPlayer(), 5);
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolor 6"))
			{
				event.setCancelled(true);
				
				sendPacket(event.getPlayer(), 100);
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolorall 0"))
			{
				event.setCancelled(true);
				
				for (Player players : Bukkit.getOnlinePlayers())
				{
					sendPacket(players, 0);
				}
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolorall 1"))
			{
				event.setCancelled(true);
				
				for (Player players : Bukkit.getOnlinePlayers())
				{
					sendPacket(players, 1);
				}
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolorall 2"))
			{
				event.setCancelled(true);
				
				for (Player players : Bukkit.getOnlinePlayers())
				{
					sendPacket(players, 2);
				}
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolorall 3"))
			{
				event.setCancelled(true);
				
				for (Player players : Bukkit.getOnlinePlayers())
				{
					sendPacket(players, 3);
				}
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolorall 4"))
			{
				event.setCancelled(true);
				
				for (Player players : Bukkit.getOnlinePlayers())
				{
					sendPacket(players, 4);
				}
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolorall 5"))
			{
				event.setCancelled(true);
				
				for (Player players : Bukkit.getOnlinePlayers())
				{
					sendPacket(players, 5);
				}
			}
			else if (event.getMessage().equalsIgnoreCase("/skycolorall 6"))
			{
				event.setCancelled(true);
				
				for (Player players : Bukkit.getOnlinePlayers())
				{
					sendPacket(players, 100);
				}
			}
		}
	}
}