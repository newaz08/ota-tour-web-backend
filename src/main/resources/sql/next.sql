SELECT DISTINCT
    tp.Id,
    tp.PackageName,
    tc.Name countryName,
    tp.TourCountryId,
    IIF(tp.CurrentMarkUp = 0.00, tp.NetPrice + (tp.NetPrice * ((SELECT TOP 1 DefaultMarkup FROM tour.TourGeneralPolicy) / 100)),
        (tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp) / 100))) AS BasePrice,
    (
        SELECT pt.TagName
        FROM tour.TourPackageWiseTagMapping AS TPWTM
                 INNER JOIN tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId
        WHERE TPWTM.TourPackageId = tp.Id AND pt.IsNextTripTag = 1 for json auto
    ) AS TagsList
FROM tour.TourPackage AS tp
inner join tour.TourCountry tc on tc.Id = tp.TourCountryId;
WHERE EXISTS (
    SELECT 1
    FROM tour.TourPackageWiseTagMapping AS TPWTM
             INNER JOIN tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId
    WHERE TPWTM.TourPackageId = tp.Id
      AND pt.IsNextTripTag = 1
);



SELECT
    tp.id AS id,
    tp.PackageName AS title,
    tc.Name AS country,
    tct.CityName AS city,
    tp.SuitableFor AS suitableFor,
    tp.CancellationText AS cancellationText,
    tp.NoOfPeopleForDisplay AS noOfPeopleForDisplay,
    format(tp.PackageStartDate,'yyyy-MM') As packageStart,
    format(tp.PackageEndDate, 'yyyy-MM') As packageEnd,
    IIF(tp.PackageMode = 'ACTIVITY', tp.ActivityDuration, tp.NoOfDays) AS duration,
    CONCAT(tp.NoOfNights, ' Nights ', tp.NoOfDays, ' Days') AS durationText,
    concat(?,'/',tpackc.Path) AS featuredPhoto,
    tp.IsGeneralDiscountApplicable           AS discounted,
    tp.GeneralDiscountPercentage                                AS discountPercent,
    IIF(tp.CurrentMarkUp = 0.00,          tp.NetPrice + (tp.NetPrice * ((SELECT
                                                                             TOP 1 DefaultMarkup
                                                                         FROM
                                                                             tour.TourGeneralPolicy) / 100)),          tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp) / 100)) AS basePrice,
    concat(?,'/',tp.BrochurePath)                                     AS brochurePath,
    (         SELECT
                  pt.id,
                  pt.TagName AS name,
                  pt.ColorCode AS colorCode
              FROM
                  tour.PackageTag pt
                      INNER JOIN
                  tour.TourPackageWiseTagMapping tpwm
                  ON pt.id = tpwm.PackageTagId
              WHERE
                      tpwm.TourPackageId = tp.id         FOR JSON AUTO      ) AS tagList,
    IIF(tp.TravelMode = 'WITH_FLIGHT', 1, 0) AS withFlight,
    IIF(tp.PackageMode = 'ACTIVITY', 1, 0) AS activity,
    (         SELECT
                  tpwtm.id
              FROM
                  tour.TourPackageWiseTagMapping tpwtm
              WHERE
                      tpwtm.TourPackageId = tp.id         FOR JSON AUTO      ) AS categoryList
FROM
    tour.TourPackage tp
        INNER JOIN
    tour.Location loc
    ON tp.LocationId = loc.id
        and tp.LocationId in(1)
        and tp.IsActive = 1 and tp.IsHajjUmrahPackage=1
        and tp.SuitableFor
        LEFT JOIN
    tour.TourCountry tc
    ON tp.TourCountryId = tc.id
        LEFT JOIN
    tour.TourCity tct
    ON tc.Id = tct.TourCountryId
        LEFT JOIN
    (SELECT
         TOP 1 Id, Path
     FROM
         tour.TourPackageContent
     WHERE
             IsFeatured = 1) tpackc
    ON tp.Id = tpackc.Id;


DECLARE @json nvarchar(max);

;WITH src (n) AS
          (
              SELECT
                  tp.id AS id,
                  tp.PackageName AS title,
                  tc.Name AS country,
                  tct.CityName AS city,
                  tp.SuitableFor AS suitableFor,
                  tp.CancellationText AS cancellationText,
                  tp.NoOfPeopleForDisplay AS noOfPeopleForDisplay,
                  format(tp.PackageStartDate,'yyyy-MM') As packageStart,
                  format(tp.PackageEndDate, 'yyyy-MM') As packageEnd,
                  IIF(tp.PackageMode = 'ACTIVITY_BASED', tp.ActivityDuration, tp.NoOfDays) AS duration,
                  IIF(tp.PackageMode = 'ACTIVITY_BASED',concat(tp.ActivityDuration,' hour(s)'),CONCAT(tp.NoOfNights, ' Nights ', tp.NoOfDays, ' Days')) AS durationText,
                  concat(?,'/',tpackc.Path) AS featuredPhoto,
                  tp.IsGeneralDiscountApplicable           AS discounted,
                  tp.GeneralDiscountPercentage                                AS discountPercent,
                  IIF(tp.CurrentMarkUp = 0.00,          tp.NetPrice + (tp.NetPrice * ((SELECT
                                                                                           TOP 1 DefaultMarkup
                                                                                       FROM
                                                                                           tour.TourGeneralPolicy) / 100)),          tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp) / 100)) AS basePrice,
                  concat(?,'/',tp.BrochurePath)                                     AS brochurePath,
                  (         SELECT
                                pt.id,
                                pt.TagName AS name,
                                pt.ColorCode AS colorCode
                            FROM
                                tour.PackageTag pt
                                    INNER JOIN
                                tour.TourPackageWiseTagMapping tpwm
                                ON pt.id = tpwm.PackageTagId
                            WHERE
                                    tpwm.TourPackageId = tp.id         FOR JSON AUTO      ) AS tagList,
                  IIF(tp.TravelMode = 'WITH_FLIGHT', 1, 0) AS withFlight,
                  IIF(tp.PackageMode = 'ACTIVITY_BASED', 1, 0) AS activity,
                  (         SELECT
                                tpwtm.id
                            FROM
                                tour.TourPackageWiseTagMapping tpwtm
                            WHERE
                                    tpwtm.TourPackageId = tp.id         FOR JSON AUTO      ) AS categoryList
              FROM
                  tour.TourPackage tp
                      INNER JOIN
                  tour.Location loc
                  ON tp.LocationId = loc.id
                      and tp.LocationId=?
                      and tp.IsActive = 1
                      LEFT JOIN
                  tour.TourCountry tc
                  ON tp.TourCountryId = tc.id
                      LEFT JOIN
                  tour.TourCity tct
                  ON tc.Id = tct.TourCountryId
                      LEFT JOIN
                  (SELECT
                       TOP 1 Id, Path
                   FROM
                       tour.TourPackageContent
                   WHERE
                           IsFeatured = 1) tpackc
                  ON tp.Id = tpackc.Id for json auto
          )
 SELECT @json = src.n
 FROM src

SELECT @json;

SELECT DISTINCT
    tp.Id,
    tp.PackageName,
    tc.Name countryName,
    tp.TourCountryId,
    IIF(tp.CurrentMarkUp = 0.00, tp.NetPrice + (tp.NetPrice * ((SELECT TOP 1 DefaultMarkup FROM tour.TourGeneralPolicy) / 100)),
        (tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp) / 100))) AS BasePrice,
    pt.TagName AS TagName
FROM tour.TourPackage AS tp
         INNER JOIN tour.TourCountry tc ON tc.Id = tp.TourCountryId
         INNER JOIN tour.TourPackageWiseTagMapping AS TPWTM ON TPWTM.TourPackageId = tp.Id
         INNER JOIN tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId
WHERE pt.IsNextTripTag = 1;

SELECT DISTINCT
    tc.Name AS countryName,
    tc.id,

    min(IIF(tp.CurrentMarkUp = 0.00, tp.NetPrice + (tp.NetPrice * ((SELECT TOP 1 DefaultMarkup FROM tour.TourGeneralPolicy) / 100)),
        (tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp) / 100)))) AS BasePrice,
    (
        SELECT (pt.TagName)
        FROM tour.TourPackageWiseTagMapping AS TPWTM
                 INNER JOIN tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId
        WHERE TPWTM.TourPackageId = tp.Id AND pt.IsNextTripTag = 1 for json path
    ) AS TagList
FROM tour.TourPackage AS tp
         INNER JOIN tour.TourCountry tc ON tc.Id = tp.TourCountryId
         INNER JOIN tour.TourPackageWiseTagMapping AS TPWTM ON TPWTM.TourPackageId = tp.Id
         INNER JOIN tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId
WHERE pt.IsNextTripTag = 1 group by tc.id, tc.Name;


SELECT DISTINCT

    tc.Name AS countryName,
    tc.id As countryId,
    MIN(
            IIF(
                        tp.CurrentMarkUp = 0.00,
                        tp.NetPrice + (tp.NetPrice * (DefaultMarkup / 100)),
                        tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp / 100))
                )
        ) AS BasePrice
--     (
--         SELECT (pt.TagName)
--         FROM tour.TourPackageWiseTagMapping AS TPWTM
--                  INNER JOIN tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId
--         WHERE TPWTM.TourPackageId = tp.Id AND pt.IsNextTripTag = 1 for json path
--     ) AS TagList
FROM tour.TourPackage AS tp
         INNER JOIN tour.TourCountry tc ON tc.Id = tp.TourCountryId
         INNER JOIN tour.TourPackageWiseTagMapping AS TPWTM ON TPWTM.TourPackageId = tp.Id
         INNER JOIN tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId
         CROSS JOIN tour.TourGeneralPolicy
WHERE pt.IsNextTripTag = 1
GROUP BY tc.id, tc.Name;

WITH BasePriceCTE AS (
    SELECT
        tp.TourCountryId AS countryId,
        MIN(
                IIF(
                            tp.CurrentMarkUp = 0.00,
                            tp.NetPrice + (tp.NetPrice * (DefaultMarkup / 100)),
                            tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp / 100))
                    )
            ) AS BasePrice
    FROM
        tour.TourPackage AS tp
            INNER JOIN
        tour.TourCountry AS tc ON tc.Id = tp.TourCountryId
            CROSS JOIN
        tour.TourGeneralPolicy
    WHERE
            tp.Id IN (
            SELECT DISTINCT TPWTM.TourPackageId
            FROM tour.TourPackageWiseTagMapping AS TPWTM
                     INNER JOIN tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId
            WHERE pt.IsNextTripTag = 1
        )
    GROUP BY
        tp.TourCountryId
)

SELECT
    tc.Name AS countryName,
    bp.countryId AS countryId,
    bp.BasePrice,
    (
        SELECT
            distinct pt.TagName as tagName
        FROM
            tour.TourPackageWiseTagMapping AS TPWTM
                INNER JOIN
            tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId
        WHERE
                TPWTM.TourPackageId IN (
                SELECT DISTINCT TPWTM.TourPackageId
                FROM tour.TourPackageWiseTagMapping AS TPWTM
                         INNER JOIN tour.PackageTag AS pt ON pt.Id = TPWTM.PackageTagId
                WHERE pt.IsNextTripTag = 1
            )
          AND pt.IsNextTripTag = 1
        FOR json path
    ) AS TagList
FROM
    tour.TourCountry AS tc
        INNER JOIN
    BasePriceCTE AS bp ON tc.Id = bp.countryId;

SELECT * FROM tour.PackageTag;




