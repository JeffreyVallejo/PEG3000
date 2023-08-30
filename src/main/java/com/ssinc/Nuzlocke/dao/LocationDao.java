package com.ssinc.Nuzlocke.dao;

import com.ssinc.Nuzlocke.model.Location;

public interface LocationDao {
    Location getLocations(String location, String version);
}
