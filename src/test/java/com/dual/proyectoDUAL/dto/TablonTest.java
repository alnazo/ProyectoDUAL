package com.dual.proyectoDUAL.dto;

import org.junit.Assert;
import org.junit.Test;
import java.sql.Timestamp;
import java.util.Date;

public class TablonTest {

    @Test
    public void timeAgo_ReturnsFormattedString() {
        Tablon tablon = new Tablon();
        Date now = new Date();
        Date createDate = new Date(now.getTime() - 3600000);
        tablon.setCreateAt(new Timestamp(createDate.getTime()));

        String timeAgo = tablon.timeAgo();

        Assert.assertNotNull(timeAgo);
    }

}
