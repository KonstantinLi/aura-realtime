package com.kpi.fict.aura.realtime.api;

import java.util.List;

public interface ExternalApiClient<T> {

    List<T> fetchEvents(String location);

}