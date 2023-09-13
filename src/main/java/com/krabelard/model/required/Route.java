package com.krabelard.model.required;

import com.krabelard.model.enums.DropOffType;
import com.krabelard.model.enums.PickupType;
import com.krabelard.model.enums.RouteType;
import com.krabelard.model.optional.Shape;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from required <code>routes.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#routestxt">the gtfs reference</a>
 */
@Value
@Builder
public class Route {
    /**
     * <code>route_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a route
     */
    String id;
    /**
     * <code>agency_id</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Agency for the specified route.
     * <br>
     * Required if multiple {@link Agency} entities are present.
     */
    Long agencyId;
    /**
     * <code>route_short_name</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Short name of a route. Often a short, abstract identifier that riders use to identify a route.
     * Both {@link Route#shortName} and {@link Route#longName} may be defined.
     * <br>
     * Required if {@link Route#longName} is empty.
     */
    String shortName;
    /**
     * <code>route_long_name</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Full name of a route. This name is generally more descriptive than the {@link Route#shortName} and often includes
     * route's destination or stop. Both {@link Route#shortName} and {@link Route#longName} may be defined
     * <br>
     * Required if {@link Route#shortName} is empty.
     */
    String longName;
    /**
     * <code>route_desc</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Description of a route that provides useful, quality information.
     * Should not be a duplicate of {@link Route#shortName} or {@link Route#longName}
     */
    String description;
    /**
     * <code>route_type</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Indicates the type of transportation used on a route.
     */
    RouteType type;
    /**
     * <code>route_url</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * URL of a web page about the particular route. Should be different from {@link Agency#url}.
     */
    String url;
    /**
     * <code>route_color</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Route color designation that matches public facing material. Defaults to <code>FFFFFF</code> when omitted or left empty.
     * Difference between {@link Route#color} and {@link Route#textColor} should provide sufficient contrast when viewed on a black and white screen.
     */
    String color;
    /**
     * <code>route_text_color</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Legible color to use for text drawn against a background of {@link Route#color}.
     * Defaults to black <code>000000</code> when omitted or left empty.
     * Difference between {@link Route#color} and {@link Route#textColor} should provide sufficient contrast when viewed on a black and white screen.
     */
    String textColor;
    /**
     * <code>route_sort_order</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Orders the routes in a way which is ideal for presentation to customers.
     * Routes with smaller {@link Route#sortOrder} value should be displayed first.
     */
    Integer sortOrder;
    /**
     * <code>continuous_pickup</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Indicates that the rider can board the transit vehicle at any point along the vehicle's travel path
     * as described by {@link Shape} entities, on every trip of the route.
     * <br>
     * Values for {@link Route#continuousPickup} may be overridden by defining values in {@link StopTime#continuousPickup}
     * for specific {@link StopTime}s along the route.
     */
    PickupType continuousPickup;
    /**
     * <code>continuous_drop_off</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Indicates that the rider can board the transit vehicle at any point along the vehicle’s travel path
     * as described by {@link Shape} entities, on every trip of the route.
     * <br>
     * Values for {@link Route#continuousDropOff} may be overridden by defining values in {@link StopTime#continuousDropOff}
     * for specific {@link StopTime}s along the route.
     */
    DropOffType continuousDropOff;
    /**
     * <code>network_id</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Identifies a group of routes. Multiple {@link Route} entities may have the same {@link Route#networkId}
     */
    Long networkId;

    public Optional<Long> getAgencyId() {
        return Optional.ofNullable(agencyId);
    }

    public Optional<String> getShortName() {
        return Optional.ofNullable(shortName);
    }

    public Optional<String> getLongName() {
        return Optional.ofNullable(longName);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    public Optional<String> getColor() {
        return Optional.ofNullable(color);
    }

    public Optional<String> getTextColor() {
        return Optional.ofNullable(textColor);
    }

    public Optional<Integer> getSortOrder() {
        return Optional.ofNullable(sortOrder);
    }

    public Optional<PickupType> getContinuousPickup() {
        return Optional.ofNullable(continuousPickup);
    }

    public Optional<DropOffType> getContinuousDropOff() {
        return Optional.ofNullable(continuousDropOff);
    }

    public Optional<Long> getNetworkId() {
        return Optional.ofNullable(networkId);
    }
}
