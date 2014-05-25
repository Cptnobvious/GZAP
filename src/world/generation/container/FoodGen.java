package world.generation.container;

import items.Item;
import items.ItemID;

import java.util.ArrayList;
import java.util.Random;

public class FoodGen {

	public static ArrayList<Item> possibilities = new ArrayList<Item>();
	
	public static ArrayList<Item> generateContents(int containerSize, int density){
		
		ArrayList<Item> returnlist = new ArrayList<Item>();
		Random rand = new Random(System.currentTimeMillis());
		
		if (possibilities.isEmpty()) {
			makePossibilities();
		}
		
		for (int x = 0; x < containerSize; x++){
			if (rand.nextInt(100) <= density){
				returnlist.add(getRandomItem(rand));
			} else {
				returnlist.add(null);
			}
		}
		
		
		return returnlist;
	}
	
	private static void makePossibilities(){
		addPossibility(ItemID.MILK);
		addPossibility(ItemID.APPLE);
		addPossibility(ItemID.WATER_BOTTLE);
		addPossibility(ItemID.NUTRIBAR);
	}
	
	private static void addPossibility(int ID){
		possibilities.add(new Item(ID));
	}
	
	private static Item getRandomItem(Random rand2){
		Random rand = new Random((System.currentTimeMillis() + rand2.nextInt()));
		
		return possibilities.get(rand.nextInt(possibilities.size()));
	}
}
