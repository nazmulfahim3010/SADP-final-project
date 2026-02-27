package pattern;
//singleton

import service.HotelService;

public class HotelSystem {

    private static HotelSystem instance;
    private HotelService hotelService;

    private HotelSystem() {
        hotelService = new HotelService();
    }

    public static HotelSystem getInstance() {
        if (instance == null) {
            instance = new HotelSystem();
        }
        return instance;
    }

    public HotelService getHotelService() {
        return hotelService;
    }
}