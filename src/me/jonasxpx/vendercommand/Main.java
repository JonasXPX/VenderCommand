package me.jonasxpx.vendercommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	private static SellItens sell = null;
	public static Economy economy = null;
	protected static List<String> inSell = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		pluginLoadConfig();
		getCommand("vender").setExecutor(new Comandos(this));
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		setupEconomy();
	}
	
	public static SellItens getSellItensClass(){
		return sell;
	}
	
	public void pluginLoadConfig(){
		reloadConfig();
		Map<ItemStack, Double> itens = new HashMap<ItemStack, Double>();
		for(String st : getConfig().getStringList("Itens")){
			String[] conf = st.split(",");
			if(!conf[0].contains(":")){
				conf[0] = conf[0]+":0";
			}
			itens.put(
					new ItemStack(
							Material.getMaterial(Integer.parseInt(conf[0].split(":")[0])),
							1,
							Short.parseShort(conf[0].split(":")[1])), Double.parseDouble(conf[1]));
		}
		sell = new SellItens(itens, getConfig().getBoolean("MostrarVendaNoConsole"));
	}
	
	/*
	 * Dependenias  --- NÃO ALTERAR! --- 
	 */
	private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	
	
	
}
