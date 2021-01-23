package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.parse.css.Rule;

import java.util.Map;

public class FontWeightStyleFactory extends AbstractStyleFactory
	{
	@Override
	public PoiStyle getStyle( Rule rule )
		{
		Map<String, String> properties = rule.getValues( );
		String style = properties.getOrDefault( "font-style", FontStyle.DEFAULT_STYLE );
		return FontWeight
				.builder( )
				.name( style )
				.build( );
		}
	}
