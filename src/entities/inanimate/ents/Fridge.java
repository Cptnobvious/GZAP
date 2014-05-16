package entities.inanimate.ents;

import util.TexInfo;
import entities.inanimate.AbstractInanimateEntity;

public class Fridge extends AbstractInanimateEntity{

	public Fridge(int x, int y, int z) {
		super(x, y, z, null);
		texinfo = new TexInfo(0, 0);
		isSolid = true;
	}

	
	
}
