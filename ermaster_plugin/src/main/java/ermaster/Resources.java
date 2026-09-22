package ermaster;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

public class Resources {

	public static final int PREFERENCE_PAGE_MARGIN_TOP = 30;

	public static final int SMALL_BUTTON_WIDTH = 70;

	public static final int MIDDLE_BUTTON_WIDTH = 120;

	public static final int LARGE_BUTTON_WIDTH = 250;

	public static final int BUTTON_ADD_REMOVE_WIDTH = 80;

	public static final int DESCRIPTION_WIDTH = 400;

	public static final int INDENT = 20;

	public static final int VERTICAL_SPACING = 15;

	public static final int MARGIN = 10;

	public static final int MARGIN_TAB = 10;

	public static final int CHECKBOX_INDENT = 5;

	public static Color PINK = new Color(Display.getCurrent(), 255, 0, 255);

	public static Color ADDED_COLOR = new Color(Display.getCurrent(), 128, 128,
			255);

	public static Color UPDATED_COLOR = new Color(Display.getCurrent(), 128,
			255, 128);

	public static Color REMOVED_COLOR = new Color(Display.getCurrent(), 255,
			128, 128);

	public static Color GRID_COLOR = new Color(Display.getCurrent(), 220, 220,
			255);

	public static Color DEFAULT_TABLE_COLOR = new Color(Display.getCurrent(),
			128, 128, 192);

	public static Color SELECTED_REFERENCED_COLUMN = new Color(
			Display.getCurrent(), 255, 230, 230);

	public static Color SELECTED_FOREIGNKEY_COLUMN = new Color(
			Display.getCurrent(), 230, 255, 230);

	public static Color SELECTED_REFERENCED_AND_FOREIGNKEY_COLUMN = new Color(
			Display.getCurrent(), 230, 230, 255);

	public static Color VERY_LIGHT_GRAY = new Color(Display.getCurrent(), 230,
			230, 230);

	public static Color LINE_COLOR = new Color(Display.getCurrent(), 180, 180,
			255);

	public static Color TEST_COLOR = new Color(Display.getCurrent(), 230, 230,
			230);

	public static final Color PRIMARY_COLOR = new Color(Display.getCurrent(),
			252, 250, 167);

	public static final Color FOREIGN_COLOR = new Color(Display.getCurrent(),
			211, 231, 245);

	public static final Color NOT_NULL_COLOR = new Color(Display.getCurrent(),
			254, 228, 207);

	/** 다크 모드 기본 캔버스 배경 색상 (어두운 회색/네이비). */
	public static final int[] DEFAULT_DARK_CANVAS_COLOR = new int[] { 40, 44, 52 };

	/** 다크 모드 기본 연결선 색상 (밝은 회백색). */
	public static final int[] DEFAULT_DARK_CONNECTION_COLOR = new int[] { 215, 215, 215 };

	/** 다크 모드 기본 그리드 선 색상 (배경에 비해 살짝 덜 어두운 색상). */
	public static final int[] DEFAULT_DARK_GRID_COLOR = new int[] { 65, 65, 75 };

	/** 다크 모드 기본 그리드 그룹핑(페이지 경계) 선 색상 (기본 그리드보다 살짝 밝은 색상). */
	public static final int[] DEFAULT_DARK_GRID_GROUP_COLOR = new int[] { 90, 92, 105 };

	private static Map<Integer, Color> colorMap = new HashMap<Integer, Color>();

	private static Map<FontInfo, Font> fontMap = new HashMap<FontInfo, Font>();

	private static class FontInfo {

		private String fontName;

		private int fontSize;

		private FontInfo(String fontName, int fontSize) {
			this.fontName = fontName;
			this.fontSize = fontSize;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((fontName == null) ? 0 : fontName.hashCode());
			result = prime * result + fontSize;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FontInfo other = (FontInfo) obj;
			if (fontName == null) {
				if (other.fontName != null)
					return false;
			} else if (!fontName.equals(other.fontName))
				return false;
			if (fontSize != other.fontSize)
				return false;
			return true;
		}
	}

	public static Color getColor(int[] rgb) {
		int key = rgb[0] * 1000000 + rgb[1] * 1000 + rgb[2];

		Color color = colorMap.get(key);

		if (color != null) {
			return color;
		}

		color = new Color(Display.getCurrent(), rgb[0], rgb[1], rgb[2]);
		colorMap.put(key, color);


		return color;
	}


	/**
	 * 현재 환경이 다크 모드인지 여부를 반환합니다.
	 * 
	 * @return 다크 모드 여부
	 */
	public static boolean isDarkMode() {


		return isDarkMode(null);
	}


	/**
	 * 지정된 컨트롤 또는 시스템 테마가 다크 모드인지 여부를 반환합니다.
	 * 
	 * @param control 검사할 컨트롤
	 * 
	 * @return 다크 모드 여부
	 */
	public static boolean isDarkMode(Control control) {
		// 1. Eclipse E4 IThemeEngine 서비스 검사
		try {
			IWorkbench workbench = PlatformUI.getWorkbench();
			if (workbench != null) {
				Object themeEngine = workbench.getService(Class.forName("org.eclipse.e4.ui.css.swt.theme.IThemeEngine"));
				if (themeEngine != null) {
					Method getActiveThemeMethod = themeEngine.getClass().getMethod("getActiveTheme");
					Object activeTheme = getActiveThemeMethod.invoke(themeEngine);
					if (activeTheme != null) {
						Method getIdMethod = activeTheme.getClass().getMethod("getId");
						String themeId = (String) getIdMethod.invoke(activeTheme);
						if (themeId != null && themeId.toLowerCase().contains("dark")) {


							return true;
						}
					}
				}
			}
		}
		catch (Throwable t) {
		}

		// 2. Eclipse Preference 서비스의 테마 ID 검사
		try {
			IPreferencesService prefService = Platform.getPreferencesService();
			if (prefService != null) {
				String themeId = prefService.getString("org.eclipse.ui.workbench", "themeId", null, null);
				if (themeId != null && themeId.toLowerCase().contains("dark")) {


					return true;
				}

				String e4ThemeId = prefService.getString("org.eclipse.e4.ui.css.swt.theme", "themeid", null, null);
				if (e4ThemeId != null && e4ThemeId.toLowerCase().contains("dark")) {


					return true;
				}
			}
		}
		catch (Throwable t) {
		}

		// 3. Shell 위젯의 전경색/배경색 검사
		try {
			Shell shell = null;
			if (control != null && ! control.isDisposed()) {
				shell = control.getShell();
			}

			if (shell == null) {
				Display display = Display.getCurrent();
				if (display == null) {
					display = Display.getDefault();
				}

				if (display != null && ! display.isDisposed()) {
					shell = display.getActiveShell();
					if (shell == null) {
						Shell[] shells = display.getShells();
						if (shells != null && shells.length > 0) {
							shell = shells[0];
						}
					}
				}
			}

			if (shell != null && ! shell.isDisposed()) {
				Color fg = shell.getForeground();
				if (fg != null) {
					double fgBrightness = (fg.getRed() * 0.299 + fg.getGreen() * 0.587 + fg.getBlue() * 0.114);
					if (fgBrightness > 150) {


						return true;
					}
				}

				Color bg = shell.getBackground();
				if (bg != null) {
					double bgBrightness = (bg.getRed() * 0.299 + bg.getGreen() * 0.587 + bg.getBlue() * 0.114);
					if (bgBrightness < 110) {


						return true;
					}
				}
			}
		}
		catch (Throwable t) {
		}

		// 4. 컨트롤 자체의 배경색이 어두운지 검사
		if (control != null && ! control.isDisposed()) {
			Color bg = control.getBackground();
			if (bg != null) {
				double brightness = (bg.getRed() * 0.299 + bg.getGreen() * 0.587 + bg.getBlue() * 0.114);
				if (brightness < 128) {


					return true;
				}
			}
		}


		return false;
	}


	/**
	 * 다크 모드 여부를 고려하여 적절한 연결선 색상을 반환합니다.
	 * 
	 * @param rgb 모델에 저장된 RGB 배열
	 * @param control 에디터 컨트롤
	 * 
	 * @return 렌더링에 사용할 Color 객체
	 */
	public static Color getConnectionColor(int[] rgb, Control control) {
		if (isDarkMode(control)) {
			if (rgb == null || (rgb[0] == 0 && rgb[1] == 0 && rgb[2] == 0)) {


				return getColor(DEFAULT_DARK_CONNECTION_COLOR);
			}

			double brightness = (rgb[0] * 0.299 + rgb[1] * 0.587 + rgb[2] * 0.114);
			if (brightness < 80) {


				return getColor(DEFAULT_DARK_CONNECTION_COLOR);
			}
		}


		return getColor(rgb);
	}


	/**
	 * 다크 모드 여부를 고려하여 적절한 그리드 선 색상을 반환합니다.
	 * 
	 * @param control 에디터 컨트롤
	 * 
	 * @return 렌더링에 사용할 Color 객체
	 */
	public static Color getGridColor(Control control) {
		if (isDarkMode(control)) {
			Color bg = null;
			if (control != null && ! control.isDisposed()) {
				bg = control.getBackground();
				if (bg != null) {
					double brightness = (bg.getRed() * 0.299 + bg.getGreen() * 0.587 + bg.getBlue() * 0.114);
					if (brightness >= 128) {
						bg = null;
					}
				}
			}

			if (bg == null) {
				Shell shell = null;
				if (control != null && ! control.isDisposed()) {
					shell = control.getShell();
				}

				if (shell == null) {
					Display display = Display.getCurrent();
					if (display == null) {
						display = Display.getDefault();
					}

					if (display != null && ! display.isDisposed()) {
						shell = display.getActiveShell();
					}
				}

				if (shell != null && ! shell.isDisposed()) {
					Color shellBg = shell.getBackground();
					if (shellBg != null) {
						double brightness = (shellBg.getRed() * 0.299 + shellBg.getGreen() * 0.587 + shellBg.getBlue() * 0.114);
						if (brightness < 128) {
							bg = shellBg;
						}
					}
				}
			}

			if (bg != null) {
				int r = Math.min(255, bg.getRed() + 25);
				int g = Math.min(255, bg.getGreen() + 25);
				int b = Math.min(255, bg.getBlue() + 35);


				return getColor(new int[] { r, g, b });
			}


			return getColor(DEFAULT_DARK_GRID_COLOR);
		}


		return GRID_COLOR;
	}


	/**
	 * 다크 모드 여부를 고려하여 적절한 그리드 그룹핑(페이지 경계) 선 색상을 반환합니다.
	 * 기본 그리드 선 색상보다 살짝 밝은 색상으로 표현됩니다.
	 * 
	 * @param control 에디터 컨트롤
	 * 
	 * @return 렌더링에 사용할 Color 객체
	 */
	public static Color getGridGroupColor(Control control) {
		if (isDarkMode(control)) {
			Color bg = null;
			if (control != null && ! control.isDisposed()) {
				bg = control.getBackground();
				if (bg != null) {
					double brightness = (bg.getRed() * 0.299 + bg.getGreen() * 0.587 + bg.getBlue() * 0.114);
					if (brightness >= 128) {
						bg = null;
					}
				}
			}

			if (bg == null) {
				Shell shell = null;
				if (control != null && ! control.isDisposed()) {
					shell = control.getShell();
				}

				if (shell == null) {
					Display display = Display.getCurrent();
					if (display == null) {
						display = Display.getDefault();
					}

					if (display != null && ! display.isDisposed()) {
						shell = display.getActiveShell();
					}
				}

				if (shell != null && ! shell.isDisposed()) {
					Color shellBg = shell.getBackground();
					if (shellBg != null) {
						double brightness = (shellBg.getRed() * 0.299 + shellBg.getGreen() * 0.587 + shellBg.getBlue() * 0.114);
						if (brightness < 128) {
							bg = shellBg;
						}
					}
				}
			}

			if (bg != null) {
				int r = Math.min(255, bg.getRed() + 45);
				int g = Math.min(255, bg.getGreen() + 45);
				int b = Math.min(255, bg.getBlue() + 55);


				return getColor(new int[] { r, g, b });
			}


			return getColor(DEFAULT_DARK_GRID_GROUP_COLOR);
		}


		return ColorConstants.lightGray;
	}


	public static void disposeColorMap() {
		for (Color color : colorMap.values()) {
			if (!color.isDisposed()) {
				color.dispose();
			}
		}

		colorMap.clear();
	}

	public static Font getFont(String fontName, int fontSize) {
		return getFont(fontName, fontSize, SWT.NORMAL);
	}

	public static Font getFont(String fontName, int fontSize, int style) {
		FontInfo fontInfo = new FontInfo(fontName, fontSize);

		Font font = fontMap.get(fontInfo);

		if (font != null) {
			return font;
		}

		font = new Font(Display.getCurrent(), fontName, fontSize, style);
		fontMap.put(fontInfo, font);

		return font;
	}

	public static void disposeFontMap() {
		for (Font font : fontMap.values()) {
			if (!font.isDisposed()) {
				font.dispose();
			}
		}

		fontMap.clear();
	}

}
