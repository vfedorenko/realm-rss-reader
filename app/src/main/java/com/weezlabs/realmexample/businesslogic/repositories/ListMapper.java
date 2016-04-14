package com.weezlabs.realmexample.businesslogic.repositories;

import com.weezlabs.realmexample.models.Plainable;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

public class ListMapper <P, R extends RealmObject & Plainable<P>> {
    public List<P> realmToPlain(RealmResults<R> realmResults) {
        List<P> result = new ArrayList<>();
        for (R realmObject : realmResults) {
            result.add(realmObject.toPlainObject());
        }

        return result;
    }
}
