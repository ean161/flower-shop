package main;

/**
 *
 * @author Flower shop
 */
abstract class Manager {
    public abstract String add();
    public abstract String update();
    public abstract String remove();
    public abstract String search();
    public abstract boolean isExistID(String id);
    public abstract boolean isEmptyList();
    public abstract void save();
}
