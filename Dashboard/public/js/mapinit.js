var map;

function init() {  
    map = new OpenLayers.Map('map', {
        projection: 'EPSG:3857',
        layers: [
            new OpenLayers.Layer.Google(
                "Google Physical",
                {type: google.maps.MapTypeId.TERRAIN}
            ),
            new OpenLayers.Layer.Google(
                "Google Streets", // the default
                {numZoomLevels: 20}
            ),
            new OpenLayers.Layer.Google(
                "Google Hybrid",
                {type: google.maps.MapTypeId.HYBRID, numZoomLevels: 20}
            ),
            new OpenLayers.Layer.Google(
                "Google Satellite",
                {type: google.maps.MapTypeId.SATELLITE, numZoomLevels: 22}
            )
        ],
        center: new OpenLayers.LonLat(-88.0309259, 15.4988252)
            // Google.v3 uses web mercator as projection, so we have to
            // transform our coordinates
            .transform('EPSG:4326', 'EPSG:3857'),
        zoom: 12
    });
    
  /*
    var layer = map.getLayersBy("visibility", true)[0];
    var heatmap = new OpenLayers.Layer.Heatmap( "Heatmap Layer", map, layer, {visible: true, radius:10}, {isBaseLayer: false, opacity: 0.3, projection: new OpenLayers.Projection("EPSG:4326")});
    map.addLayer(heatmap);
  */
   var layer = new OpenLayers.Layer.OSM();
   var heatmap = new OpenLayers.Layer.Heatmap( "Heatmap Layer", map, layer, {visible: true, radius:10}, {isBaseLayer: false, opacity: 0.3, projection: new OpenLayers.Projection("EPSG:4326")});
   map.addLayers([layer, heatmap]);
    
    //map.zoomToMaxExtent();
  
    heatmap.setDataSet(data(map));
  
    map.addControl(new OpenLayers.Control.LayerSwitcher());
    // add behavior to html
    for (var i=map.layers.length-1; i>=0; --i) {
        map.layers[i].animationEnabled = true;
    }
}

function data(map){
    var testData={
                    max: 46,
                    data: 
      [{lat:15.43457898,lon:-88.00936522,count: 10},
{lat:15.44181986,lon:-88.01960254,count: 9},
{lat:15.43027468,lon:-88.01654512,count: 8},
{lat:15.43558738,lon:-88.01011516,count: 7},
{lat:15.43206486,lon:-88.02129131,count: 6},
{lat:15.43610894,lon:-88.01200508,count: 5},
{lat:15.43896404,lon:-88.01082946,count: 4},
{lat:15.43401158,lon:-88.01779158,count: 3},
{lat:15.43469879,lon:-88.01074805,count: 2},
{lat:15.44078252,lon:-88.0088555,count: 1},
{lat:15.44089716,lon:-88.02101724,count: 0},
{lat:15.42821147,lon:-88.01184727,count: 1},
{lat:15.43383012,lon:-88.00644135,count: 2},
{lat:15.42677158,lon:-88.01737731,count: 3},
{lat:15.44115875,lon:-88.01917688,count: 4},
{lat:15.43504355,lon:-88.00608368,count: 5},
{lat:15.43376902,lon:-88.01852029,count: 6},
{lat:15.42681729,lon:-88.01883573,count: 7},
{lat:15.43874182,lon:-88.01110563,count: 8},
{lat:15.43632448,lon:-88.01355875,count: 9},
{lat:15.43953621,lon:-88.01091742,count: 10},
{lat:15.43916715,lon:-88.01403234,count: 9},
{lat:15.43965996,lon:-88.00695618,count: 8},
{lat:15.43062948,lon:-88.01178661,count: 7},
{lat:15.44217604,lon:-88.01812083,count: 6},
{lat:15.42787927,lon:-88.00849906,count: 5},
{lat:15.43413679,lon:-88.01990663,count: 4},
{lat:15.43918062,lon:-88.017626,count: 3},
{lat:15.43972931,lon:-88.02150701,count: 2},
{lat:15.43408713,lon:-88.00777612,count: 1},
{lat:15.43164122,lon:-88.02078149,count: 0},
{lat:15.43793594,lon:-88.01090081,count: 1},
{lat:15.43836424,lon:-88.02070393,count: 2},
{lat:15.43858674,lon:-88.01159616,count: 3},
{lat:15.4265526,lon:-88.01689305,count: 4},
{lat:15.43267487,lon:-88.01205387,count: 5},
{lat:15.43835674,lon:-88.00695555,count: 6},
{lat:15.4350824,lon:-88.0186656,count: 7},
{lat:15.44336656,lon:-88.01523573,count: 8},
{lat:15.42999175,lon:-88.01061129,count: 9},
{lat:15.42728325,lon:-88.01368301,count: 10},
{lat:15.43656961,lon:-88.00958919,count: 8},
{lat:15.43469972,lon:-88.00821348,count: 7},
{lat:15.4391958,lon:-88.01425988,count: 6},
{lat:15.42974539,lon:-88.01249798,count: 5},
{lat:15.43454073,lon:-88.02309023,count: 4},
{lat:15.43113008,lon:-88.01917478,count: 3},
{lat:15.43929763,lon:-88.01547662,count: 2},
{lat:15.43059381,lon:-88.00869952,count: 1},
{lat:15.43552486,lon:-88.0066827,count: 0},
{lat:15.42766308,lon:-88.02018815,count: 1},
{lat:15.43152177,lon:-88.01268199,count: 2},
{lat:15.43012889,lon:-88.01985591,count: 3},
{lat:15.43785368,lon:-88.00654756,count: 4},
{lat:15.43621645,lon:-88.01782398,count: 5},
{lat:15.43106023,lon:-88.01312105,count: 6},
{lat:15.43276548,lon:-88.01380725,count: 7},
{lat:15.43764,lon:-88.00851943,count: 8},
{lat:15.43653781,lon:-88.01899043,count: 9},
{lat:15.44041652,lon:-88.00925692,count: 10},
{lat:15.42827982,lon:-88.01957913,count: 9},
{lat:15.4279258,lon:-88.01147486,count: 8},
{lat:15.43061429,lon:-88.01920984,count: 7},
{lat:15.43269538,lon:-88.01438322,count: 6},
{lat:15.43642731,lon:-88.02122385,count: 5},
{lat:15.42642591,lon:-88.01661398,count: 4},
{lat:15.43124816,lon:-88.01079815,count: 3},
{lat:15.43919584,lon:-88.0081525,count: 2},
{lat:15.43070249,lon:-88.01316179,count: 1},
{lat:15.43510063,lon:-88.01545783,count: 0},
{lat:15.43887251,lon:-88.01380586,count: 1},
{lat:15.43637627,lon:-88.01962658,count: 2},
{lat:15.43598079,lon:-88.02000852,count: 3},
{lat:15.4275589,lon:-88.01387047,count: 4},
{lat:15.4329064,lon:-88.02056694,count: 5},
{lat:15.43057831,lon:-88.02190472,count: 6},
{lat:15.43280502,lon:-88.0092775,count: 7},
{lat:15.43842064,lon:-88.01914756,count: 8},
{lat:15.43491272,lon:-88.01915954,count: 9},
{lat:15.42813615,lon:-88.01949041,count: 10},
{lat:15.43101497,lon:-88.01951827,count: 9},
{lat:15.43849173,lon:-88.01861247,count: 8},
{lat:15.44123404,lon:-88.01853848,count: 7},
{lat:15.43449062,lon:-88.02270875,count: 6},
{lat:15.43371121,lon:-88.00800671,count: 5},
{lat:15.43842242,lon:-88.01081682,count: 4},
{lat:15.4260291,lon:-88.01662057,count: 3},
{lat:15.42709745,lon:-88.01235399,count: 2},
{lat:15.43307539,lon:-88.00864227,count: 1},
{lat:15.43730329,lon:-88.02189094,count: 0},
{lat:15.43741497,lon:-88.02030443,count: 1},
{lat:15.43500671,lon:-88.02236208,count: 2},
{lat:15.44276162,lon:-88.01666224,count: 3},
{lat:15.42870303,lon:-88.01247284,count: 4},
{lat:15.43474913,lon:-88.0223263,count: 5},
{lat:15.44254155,lon:-88.01891301,count: 6},
{lat:15.44061689,lon:-88.02064069,count: 7},
{lat:15.42890682,lon:-88.01935257,count: 8},
{lat:15.43578525,lon:-88.00628672,count: 9},
{lat:15.42710257,lon:-88.01460816,count: 10},
{lat:15.47166483,lon:-87.95248989,count: 1},
{lat:15.46356236,lon:-87.94843191,count: 2},
{lat:15.47480884,lon:-87.93918361,count: 3},
{lat:15.46996743,lon:-87.94296629,count: 4},
{lat:15.46925804,lon:-87.95018494,count: 5},
{lat:15.46896195,lon:-87.94059244,count: 6},
{lat:15.47371831,lon:-87.94658912,count: 7},
{lat:15.47661568,lon:-87.95521695,count: 8},
{lat:15.46765179,lon:-87.94288852,count: 9},
{lat:15.47290903,lon:-87.94162008,count: 10},
{lat:15.46981103,lon:-87.94618712,count: 1},
{lat:15.47242099,lon:-87.93860354,count: 2},
{lat:15.47662981,lon:-87.94529044,count: 3},
{lat:15.47552858,lon:-87.94117181,count: 4},
{lat:15.4733054,lon:-87.94724091,count: 5},
{lat:15.47868753,lon:-87.9454523,count: 6},
{lat:15.47475025,lon:-87.94319577,count: 7},
{lat:15.48027221,lon:-87.9500812,count: 8},
{lat:15.46734135,lon:-87.94245042,count: 9},
{lat:15.46708451,lon:-87.95457387,count: 10},
{lat:15.47946184,lon:-87.94692504,count: 1},
{lat:15.47682625,lon:-87.94954001,count: 2},
{lat:15.47116548,lon:-87.94889256,count: 3},
{lat:15.47132581,lon:-87.94988669,count: 4},
{lat:15.46681308,lon:-87.9434725,count: 5},
{lat:15.47137283,lon:-87.95272333,count: 6},
{lat:15.46655658,lon:-87.94119335,count: 7},
{lat:15.47141502,lon:-87.94724587,count: 8},
{lat:15.47948394,lon:-87.94986185,count: 9},
{lat:15.46427065,lon:-87.94616949,count: 10},
{lat:15.47070221,lon:-87.95094253,count: 1},
{lat:15.4638957,lon:-87.9483334,count: 2},
{lat:15.46993841,lon:-87.94879333,count: 3},
{lat:15.46447575,lon:-87.9488541,count: 4},
{lat:15.46498347,lon:-87.94406763,count: 5},
{lat:15.46631668,lon:-87.94939335,count: 6},
{lat:15.47628792,lon:-87.94998872,count: 7},
{lat:15.46854136,lon:-87.94191348,count: 8},
{lat:15.47015416,lon:-87.95606048,count: 9},
{lat:15.47842328,lon:-87.94441861,count: 10},
{lat:15.47148453,lon:-87.95504862,count: 1},
{lat:15.48035197,lon:-87.94905273,count: 2},
{lat:15.47418169,lon:-87.94563932,count: 3},
{lat:15.46931395,lon:-87.95650996,count: 4},
{lat:15.47035332,lon:-87.95143902,count: 5},
{lat:15.47166483,lon:-87.95248989,count: 1},
{lat:15.46356236,lon:-87.94843191,count: 2},
{lat:15.47480884,lon:-87.93918361,count: 3},
{lat:15.46996743,lon:-87.94296629,count: 4},
{lat:15.46925804,lon:-87.95018494,count: 5},
{lat:15.46896195,lon:-87.94059244,count: 6},
{lat:15.47371831,lon:-87.94658912,count: 7},
{lat:15.47661568,lon:-87.95521695,count: 8},
{lat:15.46765179,lon:-87.94288852,count: 9},
{lat:15.47290903,lon:-87.94162008,count: 10},
{lat:15.46981103,lon:-87.94618712,count: 1},
{lat:15.47242099,lon:-87.93860354,count: 2},
{lat:15.47662981,lon:-87.94529044,count: 3},
{lat:15.47552858,lon:-87.94117181,count: 4},
{lat:15.4733054,lon:-87.94724091,count: 5},
{lat:15.47868753,lon:-87.9454523,count: 6},
{lat:15.47475025,lon:-87.94319577,count: 7},
{lat:15.48027221,lon:-87.9500812,count: 8},
{lat:15.46734135,lon:-87.94245042,count: 9},
{lat:15.46708451,lon:-87.95457387,count: 10},
{lat:15.47946184,lon:-87.94692504,count: 1},
{lat:15.47682625,lon:-87.94954001,count: 2},
{lat:15.47116548,lon:-87.94889256,count: 3},
{lat:15.47132581,lon:-87.94988669,count: 4},
{lat:15.46681308,lon:-87.9434725,count: 5},
{lat:15.47137283,lon:-87.95272333,count: 6},
{lat:15.46655658,lon:-87.94119335,count: 7},
{lat:15.47141502,lon:-87.94724587,count: 8},
{lat:15.47948394,lon:-87.94986185,count: 9},
{lat:15.46427065,lon:-87.94616949,count: 10},
{lat:15.47070221,lon:-87.95094253,count: 1},
{lat:15.4638957,lon:-87.9483334,count: 2},
{lat:15.46993841,lon:-87.94879333,count: 3},
{lat:15.46447575,lon:-87.9488541,count: 4},
{lat:15.46498347,lon:-87.94406763,count: 5},
{lat:15.46631668,lon:-87.94939335,count: 6},
{lat:15.47628792,lon:-87.94998872,count: 7},
{lat:15.46854136,lon:-87.94191348,count: 8},
{lat:15.47015416,lon:-87.95606048,count: 9},
{lat:15.47842328,lon:-87.94441861,count: 10},
{lat:15.47148453,lon:-87.95504862,count: 1},
{lat:15.48035197,lon:-87.94905273,count: 2},
{lat:15.47418169,lon:-87.94563932,count: 3},
{lat:15.46931395,lon:-87.95650996,count: 4},
{lat:15.47035332,lon:-87.95143902,count: 5}]us
                };

    var transformedTestData = { max: testData.max , data: [] },
        data = testData.data,
        datalen = data.length,
        nudata = [];

    /*
  data: [{lat: 15.5272946, lon:-87.9631734, count: 9},
                   {lat: 15.4767496, lon:-87.9573798, count: 7},
                   {lat: 15.4698476, lon:-87.9468656, count: 2},
                   {lat: 15.4345537, lon:-88.0094576, count: 4}]
    */
  
    // in order to use the OpenLayers Heatmap Layer we have to transform our data into 
    // { max: <max>, data: [{lonlat: <OpenLayers.LonLat>, count: <count>},...]}

    while(datalen--){
        nudata.push({
            lonlat: new OpenLayers.LonLat(data[datalen].lon, data[datalen].lat),
            count: data[datalen].count
        });
    }

    transformedTestData.data = nudata;

    return transformedTestData;
}