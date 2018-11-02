package inf101.v18.rogue101.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import inf101.v18.gfx.textmode.Printer;
import inf101.v18.rogue101.game.IGame;

public class Container<T extends IItem> implements IContainer<T>, Collection<T>{
	List<T> bag;
	private int maxSpace;
	private int usedSpace;
	private int maxElements;
	
	public Container(int maxSpace, int maxElements) {
		this.bag = new ArrayList<>();
		this.maxSpace = maxSpace;
		this.usedSpace = 0;
		this.maxElements = maxElements;
	}
	
	@Override
	public int size() {
		return usedSpace;
	}
	
	@Override
	public int nElements() {
		return bag.size();
	}

	@Override
	public boolean isEmpty() {
		return bag.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return bag.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(T e) {
		if (isSpace(e)) {
			bag.add(e);
			usedSpace += e.getSize();
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {
		if (!bag.isEmpty() && bag.contains(o)) {
			bag.remove(o);
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return bag.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return bag.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return bag.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return bag.retainAll(c);
	}

	@Override
	public void clear() {
		bag.clear();
		
	}

	
	//fffffff
	
	@Override
	public void putIn(T e) {
		add(e);
	}

	@Override
	public T takeOut() {
		if (!bag.isEmpty()) {
			usedSpace -= bag.get(0).getSize();
			return bag.remove(0);
		}
		return null;
	}

	@Override
	public boolean isSpace(T e) {
		if (bag.size()==maxElements) {
			return false;
		}
		return (size() + e.getSize() <= getMaxSpace());
	}

	// Displays everything in the bag to the right of the screen.
	@Override
	public int displayContent(int startIndex, Printer printer) {
		for (int i = 0; i<bag.size(); i++) {
			
			String name = bag.get(i).getName();
			name = String.format("%-10s", name);
			
			printer.printAt(45, startIndex++, "- " + name);
		}
		return startIndex;
	}

	@Override
	public int getMaxSpace() {
		return maxSpace;
	}
	
	public int getAllMoney() {
		int sum = 0;
		for (int i=0; i<bag.size(); i++) {
			sum += ((Money) bag.get(i)).getMoney();
		}
		return sum;
	}
	
	public int getAllArmor() {
		int sum = 0;
		for (int i=0; i<bag.size(); i++) {
			sum += ((Armor) bag.get(i)).wearArmor();
		}
		return sum;
	}
	
	public int getAllWeapon() {
		int sum = 0;
		for (int i=0; i<bag.size(); i++) {
			sum += ((Weapon) bag.get(i)).wieldWeapon();
		}
		return sum;
	}
	
	@Override
	public ArrayList<T> getAll() {
		return (ArrayList<T>) bag;
	}
	
	@Override
	public T getElement(int n) {
		return bag.get(n);
	}
	
	public boolean containsSunglasses() {
		for (int i=0; i<bag.size(); i++) {
			if (bag.get(i).getName().equals("sunglasses")) {
				return true;
			}
		}
		return false;
	}
	
	public void dropSomething(IGame game) {
		T item = takeOut();
		if (game.drop(item)) {
			game.displayMessage("dropped " + item.getName());
			remove(item);
			return;
		}
	}

}
