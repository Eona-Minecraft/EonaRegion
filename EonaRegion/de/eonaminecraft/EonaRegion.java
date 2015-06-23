package eonaminecraft;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class EonaRegion extends JavaPlugin{

	private Logger l = null;
	
	public void onEnable(){
		l = this.getLogger();
		l.info("EonaRegion geladen");
	}
	
	
	public boolean onCommand(CommandSender sender,Command command,String label,String[] args){
		if(command.getName().equalsIgnoreCase("setRegion")){
			if (sender.hasPermission("eona.setRegion") || sender.isOp()){
				if(args.length >= 2){
					String regionN = args[0];
					String spieler = args[1];
					int prio = 4;
					try{
						prio = Integer.parseInt(args[2]);
					}catch(Exception e){
						
					}
					l.info(sender.getName() + ": regionName=" + regionN + " | player=" + spieler + " | prio=" + prio);
					setRegion(regionN, spieler, prio, getPlayerOfCommand(sender));
					return true;
				}else{
					sender.sendMessage("Ungültige Anzahl Parameter");
					return false;
				}
			}else{
				sender.sendMessage("Keiner Permissions oder kein Operator");
				return false;
			}
		}else{
			return false;
		}
	}
	
	public void setRegion(String regionName, String spielerName, int prio, Player executer){
		executer.performCommand("region d " + regionName );
		executer.performCommand("region addowner " + regionName + " " + spielerName );
		executer.performCommand("region setpriority " + regionName + " " + prio );
	}
	
	public Player getPlayerOfCommand(CommandSender x){
		return this.getServer().getPlayer(x.getName());
	}
	

	
}
