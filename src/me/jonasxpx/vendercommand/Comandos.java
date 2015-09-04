package me.jonasxpx.vendercommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comandos implements CommandExecutor{
	
	private Main plugin;
	
	protected Comandos(Main plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("§cEste comando não deve ser executado no console!.");
			return true;
		}
		if(!sender.hasPermission("vender.use")){
			sender.sendMessage("§cSem permissão para usar este comando:§6 vender.use");
			return true;
		}
		if(args.length >= 1 ){
			if(args[0].equalsIgnoreCase("tudo")){
				SellManager.sellFullItem((Player)sender);
				return true;
			}
			if(args[0].equalsIgnoreCase("slot")){
				SellManager.SellSlotItem((Player)sender);
				return true;
			}
			if(args[0].equalsIgnoreCase("reload")){
				if(sender.isOp() || sender.hasPermission("vender.reload")){
					plugin.reloadConfig();
					sender.sendMessage("§cConfiguração recarregada!");
					return true;
				}
			}
		}
		sender.sendMessage("§cUse /vender <tudo, slot>"
				+ "\n tudo: vende todos os itens possíveis do inventario."
				+ "\n slot: vende apenas o item em sua mão");
		return true;
	}
}
