package inf101.v18.rogue101.objects;

import java.util.ArrayList;

import inf101.v18.gfx.textmode.Printer;
import inf101.v18.rogue101.game.IGame;

public interface IContainer<T extends IItem> {
	
	void putIn(T e);
	
	T takeOut();
	
	T getElement(int n);
	
	boolean isSpace(T e);
	
	int getMaxSpace();
	
	int nElements();
	
	ArrayList<T> getAll();

	int displayContent(int startIndex, Printer printer);
	
	
}
