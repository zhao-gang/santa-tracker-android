/*
 * Copyright (C) 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.apps.santatracker.presentquest.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.google.android.apps.santatracker.presentquest.vo.Place;

import java.util.List;

@Dao
public interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPlace(Place place);

    @Update
    int updatePlace(Place place);

    @Query("DELETE FROM place")
    void deleteAll();

    @Query("SELECT * FROM place")
    List<Place> getAll();

    @Query("SELECT COUNT(*) FROM place")
    int count();

    @Query("SELECT * FROM place WHERE lat = :latitude AND lng = :longitude")
    Place getByLatLong(double latitude, double longitude);

    @Query(
            "DELETE FROM place WHERE id IN (SELECT id FROM place ORDER BY id ASC LIMIT :numberToCull)")
    void deleteOldestById(int numberToCull);
}
