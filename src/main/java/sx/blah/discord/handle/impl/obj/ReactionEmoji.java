/*
 *     This file is part of Discord4J.
 *
 *     Discord4J is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Discord4J is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with Discord4J.  If not, see <http://www.gnu.org/licenses/>.
 */

package sx.blah.discord.handle.impl.obj;

import sx.blah.discord.handle.obj.IEmoji;
import sx.blah.discord.handle.obj.IIDLinkedObject;

import java.util.Objects;

/**
 * Represents an emoji on a reaction. This can either be a unicode or custom guild emoji. In the case of the former,
 * {@link #id} is 0. This is a value-based class.
 */
public class ReactionEmoji implements IIDLinkedObject {

	/**
	 * Constructs a {@link ReactionEmoji} from the name and ID of the given guild emoji.
	 *
	 * @param emoji The emoji to get a name and ID from.
	 * @return A reaction emoji with the name and ID of the given guild emoji.
	 */
	public static ReactionEmoji fromGuildEmoji(IEmoji emoji) {
		return fromGuildEmoji(emoji.getName(), emoji.getLongID());
	}

	/**
	 * Constructs a {@link ReactionEmoji} from the given name and ID.
	 *
	 * @param name The name of the emoji.
	 * @param id The ID of the emoji.
	 * @return A reaction emoji with the given name and ID.
	 */
	public static ReactionEmoji fromGuildEmoji(String name, long id) {
		return new ReactionEmoji(name, id);
	}

	/**
	 * Constructs a {@link ReactionEmoji} from the given unicode emoji.
	 *
	 * @param unicode The unicode emoji.
	 * @return A reaction emoji with the given unicode emoji.
	 */
	public static ReactionEmoji fromUnicode(String unicode) {
		return new ReactionEmoji(unicode, 0L);
	}

	private final String name;
	private final long id;

	private ReactionEmoji(String name, long id) {
		this.name = name;
		this.id = id;
	}

	/**
	 * Gets the name of the emoji. If this emoji is a unicode emoji, it returns the unicode character.
	 *
	 * @return The name of the emoji or the unicode character.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Whether the emoji is a unicode emoji.
	 *
	 * @return Whether the emoji is a unicode emoji.
	 */
	public boolean isUnicode() {
		return id == 0;
	}

	@Override
	public long getLongID() {
		return id;
	}

	@Override
	public String toString() {
		return isUnicode() ? getName() : "<" + getName() + ":" + Long.toUnsignedString(getLongID()) + ">";
	}

	@Override
	public boolean equals(Object obj) {
		if (!this.getClass().isAssignableFrom(obj.getClass())) return false;

		ReactionEmoji other = (ReactionEmoji) obj;
		return other.name.equals(this.name) && other.id == this.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, id);
	}
}
