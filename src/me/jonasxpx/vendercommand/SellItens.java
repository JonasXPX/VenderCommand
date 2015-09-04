package me.jonasxpx.vendercommand;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SellItens {

	private Map<ItemStack, Double> itens = new HashMap<ItemStack, Double>();
	private final boolean consoleDebug;
	
	public SellItens(Map<ItemStack, Double> itens2, boolean consoleDebug) {
		this.itens.putAll(itens2);
		this.consoleDebug = consoleDebug;
	}
	
	
	public void sellItens(final ItemStack material, final Player player){
		double vLocal = 0;
		for(final ItemStack item : itens.keySet()){
			if(item.isSimilar(material)){
				vLocal = (itens.get(item) * material.getAmount());
				player.sendMessage(
						"§2Vendido: §f" + material.getAmount() 
						+ " "+ material.getType().name().toLowerCase() 
						+  "§2 por §f" + itens.get(item) 
						+ "§2 Total: §f" 
						+ NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR")).format(vLocal));
				player.getInventory().removeItem(material);
				if(consoleDebug)
					System.out.println(player.getName() + " - Vendeu "  + material.getAmount() + " "+ material.getType().name().toLowerCase() +  " por " + itens.get(item) + " cada Total: " + (itens.get(item) * material.getAmount()));
			}
		}
		Main.economy.depositPlayer(player, vLocal);
	}
}
