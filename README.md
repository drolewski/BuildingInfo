# BuildingInfo
[![Build Status](https://travis-ci.org/drolewski/BuildingInfo.svg?branch=master)](https://travis-ci.org/drolewski/BuildingInfo)

## Install
Download and install [Mongodb](https://www.mongodb.com/download-center/community)

After installation create in your local disk directory with name: **data**

Inside this catalog Mongo will create collection files.

## Requests

1. Room: 
    * [GET] /room -> return all rooms
    * [GET] /room/{id} -> return one room or throw error
    * [GET] /room/surface/{id} -> return surface of given room
    * [GET] /room/cubature/{id} -> return cubature of given room
    * [GET] /room/lighting/{id} -> return lighting of given room
    * [GET] /room/heating/{id} -> return heating of given room
    * [POST] /room -> add new room taken from request body to db
    * [PUT] /room -> modify room
    * [DELETE] /room/{id} -> delete given room
    
2. Floor:
    * [GET] /floor -> return all floors
    * [GET] /floor/{id} -> return one floor or throw error
    * [GET] /floor/surface/{id} -> return surface of given floor
    * [GET] /floor/cubature/{id} -> return cubature of given floor
    * [GET] /floor/lighting/{id} -> return lighting of given floor
    * [GET] /floor/heating/{id} -> return heating of given floor
    * [POST] /floor -> add new floor taken from request body to db
    * [PUT] /floor -> modify floor
    * [DELETE] /floor/{id} -> delete given floor

3. Building:
    * [GET] /building -> return all buildings
    * [GET] /building/{id} -> return one building or throw error
    * [GET] /building/surface/{id} -> return surface of given building
    * [GET] /building/cubature/{id} -> return cubature of given building
    * [GET] /building/lighting/{id} -> return lighting of given building
    * [GET] /building/heating/{id} -> return heating of given building
    * [POST] /building -> add new building taken from request body to db
    * [PUT] /building -> modify building
    * [DELETE] /building/{id} -> delete given building
    
4. General:
    * [GET] / -> return all building
    
## Request Body
* Room:

```json
    {    
        "id": 1,
        "name": "test",
        "surface": 123.0,
        "cubature": 400.0,
        "heating": 156.0,
        "lighting": 210.0    
    }
```
    
* Level:
    
```json 
    {
        "id": 1,
        "name": "testLvL",
        "immoveables": [{
            "id": 1,
            "name": "testRoom",
            "surface": 123.0,
            "cubature": 400.0,
            "heating": 156.0,
            "lighting": 210.0   
        },
        {
            "id": 1,
            "name": "testRoom",
            "surface": 65.0,
            "cubature": 190.0,
            "heating": 90.0,
            "lighting": 100.0 
        }]
    }
```
    
* Building:

```json
    {
            "id": 1,
            "name": "testBuilding",
            "immoveables":[{
                "id": 1,
                "name": "testLvL",
                "immoveables": [{
                    "id": 1,
                    "name": "testRoom",
                    "surface": 123.0,
                    "cubature": 400.0,
                    "heating": 156.0,
                    "lighting": 210.0   
                },
                {
                    "id": 1,
                    "name": "testRoom",
                    "surface": 65.0,
                    "cubature": 190.0,
                    "heating": 90.0,
                    "lighting": 100.0 
                }]
            }]
    }
```

# Authors
[Dominik Mistera](https://github.com/DMistera)

[Mateusz Ratajczak](https://github.com/mateuszratajczak)

[Szymon Rozynek](https://github.com/SzymonRozynek)

[Dominik Rolewski](https://github.com/drolewski)
