package world.tile.basetiles;

import gzap.Boot;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import util.PointMath;
import world.tile.Tile;
import world.tile.TileEntity;

public class TileDoorEntity extends TileEntity{
	
	boolean oldmouse = false;
	int mousetimer = 0;
	
	public TileDoorEntity(Tile parent) {
		super(parent);
	}

	@Override
	public void update() {
		if (mousetimer == 0){
			mousetimer = 0;
			oldmouse = false;
		} else {
			mousetimer--;
		}
	}

	@Override
	public void getMouseEvent(int button) {
		if (button == 0 && !oldmouse){
			double distance = PointMath.distance2Points(parent.getMapX(), parent.getMapY(), Boot.getPlayer().getX(), Boot.getPlayer().getY());
			if (distance < 2){
				parent.setSolid(!parent.isSolid());
				int oldmeta = parent.getMetadata();
				if (oldmeta == 1){
					parent.setMetadata(0);
				} else {
					parent.setMetadata(1);
				}
				
				mousetimer = 20;
				oldmouse = true;
			}
		}
	}

	@Override
	public void save(FileOutputStream out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(FileInputStream in) {
		// TODO Auto-generated method stub
		
	}

	
	
}
