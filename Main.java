
public class Main {
    public static void main(String[] args) {
        PetrolStation petrolStation = new PetrolStation();
        petrolStation.addFuelToStation(10000.0);
        petrolStation.doRefuel(50.0);
        petrolStation.doRefuel(75.5);
        petrolStation.doRefuel(75.5);
        petrolStation.doRefuel(75.5);
        petrolStation.doRefuel(30.5);
        petrolStation.doRefuel(30.5);
        petrolStation.doRefuel(30.5);
        petrolStation.doRefuel(25.0);
        petrolStation.doRefuel(25.0);
        petrolStation.doRefuel(250.0);
        petrolStation.doRefuel(25.0);
        petrolStation.doRefuel(251.0);
        petrolStation.doRefuel(25.0);
        petrolStation.doRefuel(55.0);
        petrolStation.doRefuel(25.0);
        petrolStation.doRefuel(81.0);

        ThreadSafeList<Integer> threadSafeList = new ThreadSafeList<>();
        new Thread(()->threadSafeList.add(7)).start();
        new Thread(()->threadSafeList.add(5)).start();
        new Thread(()->threadSafeList.add(11)).start();
        new Thread(()->threadSafeList.add(13)).start();
//        new Thread(()->threadSafeList.remove(3)).start();
//        new Thread(()->threadSafeList.remove(0)).start();
//        new Thread(()->threadSafeList.removeByObject(7)).start();
        int size = threadSafeList.getSize();
        System.out.println(size);
        Integer integer = threadSafeList.get(0);
        Integer integer1 = threadSafeList.get(2);
        System.out.println(integer);
        System.out.println(integer1);
    }
}