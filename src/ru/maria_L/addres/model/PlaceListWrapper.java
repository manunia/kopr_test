package ru.maria_L.addres.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "places")
public class PlaceListWrapper {

    private List<Place> places;
}
