package me.jonasxpx.vendercommand;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Listeners implements Listener{

	@EventHandler
	public void onClickInvent(InventoryClickEvent e){
		if(Main.inSell.contains(e.getWhoClicked().getName())){
			e.setCancelled(true);
		}
	}
	
	
	
}
