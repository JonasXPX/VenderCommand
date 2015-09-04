package me.jonasxpx.vendercommand;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SellManager {
	
	
	public static void sellFullItem(final Player player){
		final Inventory inv = player.getInventory();
		Main.inSell.add(player.getName());
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(ItemStack item : inv.getContents()){
					if(item != null)
						Main.getSellItensClass().sellItens(item, player);
				}
				Main.inSell.remove(player.getName());
			}
		}).start();
		
	}
	
	public static void SellSlotItem(Player player){
		Main.getSellItensClass().sellItens(player.getItemInHand(), player);
	}

}
