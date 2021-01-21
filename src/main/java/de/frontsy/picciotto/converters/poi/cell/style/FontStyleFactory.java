package de.frontsy.picciotto.converters.poi.cell.style;

import de.frontsy.picciotto.parse.css.Rule;

import java.util.Map;

public class FontStyleFactory extends AbstractStyleFactory
	{
	@Override
	public PoiStyle getStyle( Rule rule )
		{
		Map<String, String> properties = rule.getValues( );
		String color = properties.getOrDefault( "color", Font.DEFAULT_COLOR );
		String family = properties.getOrDefault( "font-family", Font.DEFAULT_FAMILY );
		return Font
				.builder( )
				.color( color )
				.fontFamily(family)
				.build( );
		}
	}
