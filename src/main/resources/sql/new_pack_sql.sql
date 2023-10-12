SELECT
    tp.id AS id,
    tp.PackageName AS title,
    tc.Name AS country,
    tct.CityName AS city,
--     tp.SuitableFor AS suitableFor,
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
        and tp.IsActive = 1 and tp.IsHajjUmrahPackage=0

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
    ON tp.Id = tpackc.Id

    inner join (select TourPackageId from tour.TourPackageWiseTagMapping tpm
                inner join tour.PackageTag pt on tpm.PackageTagId = pt.Id
                left join tour.TagIdentifier ti on pt.TagIdentifierId = ti.Id
                where ti.Name='Local Experiences'
                                     group by TourPackageId)
        tpwtm on tp.Id = tpwtm.TourPackageId;