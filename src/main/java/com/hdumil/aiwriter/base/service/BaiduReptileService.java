package com.hdumil.aiwriter.base.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BaiduReptileService {
    List<Map<String,String>> imageReptile(String Word, int count_max, boolean random) throws IOException;
}
