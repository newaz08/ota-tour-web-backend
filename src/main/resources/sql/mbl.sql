select tp.Id, tp.PackageName, tp.LocationId locationId, tc.Name countryName,
       IIF(tp.CurrentMarkUp = 0.00, tp.NetPrice + (tp.NetPrice * (
           (SELECT TOP 1 DefaultMarkup FROM tour.TourGeneralPolicy) / 100)),
            tp.NetPrice + (tp.NetPrice * (tp.CurrentMarkUp) / 100)) AS basePrice,
       tp.GeneralDiscountPercentage AS discountPercent, concat(?,'/',tpackc.Path) AS featuredPhoto  from tour.TourPackage tp
    inner join tour.TourPackageWiseTagMapping TPWTM on tp.Id = TPWTM.TourPackageId
    inner join tour.TourCountry tc on tp.TourCountryId = tc.Id
    left join
    (SELECT TOP 1 Id, TourPackageId,Path FROM tour.TourPackageContent WHERE IsFeatured = 1) tpackc
        on tp.id = tpackc.TourPackageId;