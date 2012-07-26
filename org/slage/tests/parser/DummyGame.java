package org.slage.tests.parser;

import org.slage.SlageGame;
import org.slage.parser.BunchOfItems;

public class DummyGame
		extends SlageGame {
	private BunchOfItems currentRoom;
	private BunchOfItems theInventory;

	public DummyGame(BunchOfItems room, BunchOfItems inventory) {
		super("Dummy game");
		currentRoom = room;
		theInventory = inventory;
	}

	public BunchOfItems getRoomForParser() {
		return currentRoom;
	}

	public BunchOfItems getInventoryForParser() {
		return theInventory;
	}
}
