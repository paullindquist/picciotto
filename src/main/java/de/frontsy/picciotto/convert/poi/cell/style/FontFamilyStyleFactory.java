package de.frontsy.picciotto.convert.poi.cell.style;

import de.frontsy.picciotto.parse.css.Rule;

import java.util.Map;

public class FontFamilyStyleFactory extends AbstractStyleFactory
	{
	@Override
	public PoiStyle getStyle( Rule rule )
		{
		Map<String, String> properties = rule.getValues();
		String family = properties.getOrDefault( "font-family", FontFamily.DEFAULT_FAMILY );
		return FontFamily
				.builder( )
				.name( family )
				.build( );
		}
	}
