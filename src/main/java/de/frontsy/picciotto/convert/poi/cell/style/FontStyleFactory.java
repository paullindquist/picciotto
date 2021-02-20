package de.frontsy.picciotto.convert.poi.cell.style;

import de.frontsy.picciotto.parse.css.Rule;

import java.util.Map;

public class FontStyleFactory extends AbstractStyleFactory
	{
	@Override
	public PoiStyle getStyle( Rule rule )
		{
		Map<String, String> properties = rule.getValues( );
		String style = properties.getOrDefault( "font-style", FontStyle.DEFAULT_STYLE );
		return FontStyle
				.builder( )
				.name( style )
				.build( );
		}
	}
