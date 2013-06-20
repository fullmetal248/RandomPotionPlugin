package net.mctk.randompotion;

import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomPotionPlugin extends JavaPlugin{
	Logger log;
	int maxeffectlevel;
	int effectamount;
	int potionamount;
	Server server;
	@Override
	public void onEnable(){
		this.saveDefaultConfig();
		Server server = this.getServer();
		if(!server.getOnlineMode()){
			log.info("�T�[�o�̓I�t���C�����[�h�œ��삵�Ă��܂��B");
			log.info("RandomPotion�v���O�C���𖳌������܂��B");
			this.setEnabled(false);
		}
		maxeffectlevel = this.getConfig().getInt("maxeffectlevel");
		effectamount = this.getConfig().getInt("effectamount");
		potionamount = this.getConfig().getInt("potionamount");
		
		getCommand("randompotion").setExecutor(new RandomPotionCommandExecutor(this, maxeffectlevel, effectamount, potionamount));
	}
	
	@Override
	public void onDisable(){
		
	}

}
class Check{
	Server server;
	public Check(Server server){
		this.server = server;
	}
	
}