package net.mctk.randompotion;

import java.util.Random;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class RandomPotionCommandExecutor implements CommandExecutor{
	ItemStack itemstack;
	RandomPotionPlugin plugin;
	final static int POTION_EFFECT_SIZE = 18;
	//�����I�Ȓ萔
	static int MAX_EFFECT_LEVEL;
	static int EFFECT_AMOUNT;
	static int POTION_AMOUNT;
	
	
	public RandomPotionCommandExecutor(RandomPotionPlugin plugin, int maxEffectLevel, int effectAmount, int potionAmount){
		this.plugin = plugin;
		MAX_EFFECT_LEVEL = maxEffectLevel;
		EFFECT_AMOUNT = effectAmount;
		POTION_AMOUNT = potionAmount;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 0){
			return false;
		}
		if(args[0].equals("test")){
			if(!(sender instanceof Player)){
				sender.sendMessage("�v���C���[�������̃R�}���h�g���Ȃ���I");
				return false;
			}
			Player player = (Player)sender;
			PlayerInventory inventory = player.getInventory();
			//ItemStack itemstack = new ItemStack(373, 64, (short)16);
			Potion potion = new Potion(PotionType.SPEED, 1);//�_�~�[�̃|�[�V����
			itemstack = potion.toItemStack(64);//�|�[�V�����̐���64�Ɏw��
			PotionMeta potionmeta = (PotionMeta) itemstack.getItemMeta();//ItemMeta���L���X�g����PotionMeta��
			//���̕ӂ���|�[�V�����̃f�[�^������
			potionmeta.addCustomEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 7200, 127), true);//�����PotionMeta�̕ҏW���ł���
			potionmeta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 7200, 127), true);

			//�����܂�
			itemstack.setItemMeta(potionmeta);//�����������ʂ�K�p
			inventory.addItem(itemstack);//����ƃA�C�e���ǉ��ł���
			
			return true;
			
		}
		else if(args[0].equals("randp")){
			//Random random = new Random();
			if(!(sender instanceof Player)){
				sender.sendMessage("�v���C���[�������̃R�}���h�g���Ȃ���I");
				return false;
			}

			PotionEffectType[] pet = new PotionEffectType[POTION_EFFECT_SIZE];
			int i = 0;
			//�߂�ǂ�������ł�
			pet[i++] = PotionEffectType.BLINDNESS;
			pet[i++] = PotionEffectType.CONFUSION;
			pet[i++] = PotionEffectType.DAMAGE_RESISTANCE;
			pet[i++] = PotionEffectType.FAST_DIGGING;
			pet[i++] = PotionEffectType.FIRE_RESISTANCE;
			//pet[i++] = PotionEffectType.HARM;
			//pet[i++] = PotionEffectType.HEAL;
			pet[i++] = PotionEffectType.HUNGER;
			pet[i++] = PotionEffectType.INCREASE_DAMAGE;
			pet[i++] = PotionEffectType.INVISIBILITY;
			pet[i++] = PotionEffectType.JUMP;
			pet[i++] = PotionEffectType.NIGHT_VISION;
			pet[i++] = PotionEffectType.POISON;
			pet[i++] = PotionEffectType.REGENERATION;
			pet[i++] = PotionEffectType.SLOW;
			pet[i++] = PotionEffectType.SLOW_DIGGING;
			pet[i++] = PotionEffectType.SPEED;
			pet[i++] = PotionEffectType.WATER_BREATHING;
			pet[i++] = PotionEffectType.WEAKNESS;
			pet[i++] = PotionEffectType.WITHER;	
			//�I���
			Player player = (Player)sender;
			PlayerInventory playerInventory = player.getInventory();
			Potion potion = new Potion(PotionType.WATER);
			potion.setSplash(true);
			itemstack = potion.toItemStack(POTION_AMOUNT);
			PotionMeta potionMeta = (PotionMeta)itemstack.getItemMeta();
			//���ʒǉ�����
			long seed = System.currentTimeMillis();
			Random rand1 = new Random(seed);
			Random rand2 = new Random(seed);
			for(int j=0; j<EFFECT_AMOUNT; j++){
				int levelRandom = rand1.nextInt(MAX_EFFECT_LEVEL);
				int effectRandom = rand2.nextInt(POTION_EFFECT_SIZE);
				potionMeta.addCustomEffect(new PotionEffect(pet[effectRandom], 7200, levelRandom), false);
				//System.out.println(effectRandom);
			}
			
			itemstack.setItemMeta(potionMeta);
			playerInventory.addItem(itemstack);
			return true;
		}
		return false;
	}
	
}
