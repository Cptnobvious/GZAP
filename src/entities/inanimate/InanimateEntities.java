package entities.inanimate;

import entities.inanimate.ents.Fridge;
import gzap.GameRegistry;

public class InanimateEntities {

	public static void initialize(){
		GameRegistry.registerInEnt(InanimateIDs.FRIDGE, new Fridge());
	}
	
}
