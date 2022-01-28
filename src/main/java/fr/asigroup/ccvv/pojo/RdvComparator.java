package fr.asigroup.ccvv.pojo;

import fr.asigroup.ccvv.entity.Rdv;

import java.util.Comparator;

public class RdvComparator implements Comparator<Rdv> {

    @Override
    public int compare(Rdv rdv1, Rdv rdv2) {
        if (rdv1.getDate().isBefore(rdv2.getDate())) {
            return -1;
        }

        if (rdv1.getDate().isAfter(rdv2.getDate())) {
            return 1;
        }

        if (rdv1.getTime().isBefore(rdv2.getTime())) {
            return -1;
        }

        if (rdv1.getTime().isAfter(rdv2.getTime())) {
            return 1;
        }

        return 0;
    }

    @Override
    public Comparator<Rdv> reversed() {
        return Comparator.super.reversed();
    }
}
