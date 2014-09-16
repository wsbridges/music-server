package org.bill.bridges.itunes.pojo;

public class Track {
	private String trackId;
	private String name;
	private String artist;
	private String albumArtist;
	private String album;
	private String genre;
	private Integer totalTime;
	private Integer discNumber;
	private Integer discCount;
	private Integer trackNumber;
	private Integer trackCount;
	private Integer year;
	private String location;
	
	/**
	 * @return the trackId
	 */
	public String getTrackId() {
		return trackId;
	}
	/**
	 * @param trackId the trackId to set
	 */
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
	 * @return the albumArtist
	 */
	public String getAlbumArtist() {
		return albumArtist;
	}
	/**
	 * @param albumArtist the albumArtist to set
	 */
	public void setAlbumArtist(String albumArtist) {
		this.albumArtist = albumArtist;
	}
	
	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}
	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}
	
	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	/**
	 * @return the totalTime
	 */
	public Integer getTotalTime() {
		return totalTime;
	}
	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
	
	/**
	 * @return the discNumber
	 */
	public Integer getDiscNumber() {
		return discNumber;
	}
	/**
	 * @param discNumber the discNumber to set
	 */
	public void setDiscNumber(Integer discNumber) {
		this.discNumber = discNumber;
	}
	
	/**
	 * @return the discCount
	 */
	public Integer getDiscCount() {
		return discCount;
	}
	/**
	 * @param discCount the discCount to set
	 */
	public void setDiscCount(Integer discCount) {
		this.discCount = discCount;
	}
	
	/**
	 * @return the trackNumber
	 */
	public Integer getTrackNumber() {
		return trackNumber;
	}
	/**
	 * @param trackNumber the trackNumber to set
	 */
	public void setTrackNumber(Integer trackNumber) {
		this.trackNumber = trackNumber;
	}
	
	/**
	 * @return the trackCount
	 */
	public Integer getTrackCount() {
		return trackCount;
	}
	/**
	 * @param trackCount the trackCount to set
	 */
	public void setTrackCount(Integer trackCount) {
		this.trackCount = trackCount;
	}
	
	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", name=" + name + ", artist="
				+ artist + ", albumArtist=" + albumArtist + ", album=" + album
				+ ", genre=" + genre + ", totalTime=" + totalTime
				+ ", discNumber=" + discNumber + ", discCount=" + discCount
				+ ", trackNumber=" + trackNumber + ", trackCount=" + trackCount
				+ ", year=" + year + ", location=" + location + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((album == null) ? 0 : album.hashCode());
		result = prime * result
				+ ((albumArtist == null) ? 0 : albumArtist.hashCode());
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result
				+ ((discCount == null) ? 0 : discCount.hashCode());
		result = prime * result
				+ ((discNumber == null) ? 0 : discNumber.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((totalTime == null) ? 0 : totalTime.hashCode());
		result = prime * result
				+ ((trackCount == null) ? 0 : trackCount.hashCode());
		result = prime * result + ((trackId == null) ? 0 : trackId.hashCode());
		result = prime * result
				+ ((trackNumber == null) ? 0 : trackNumber.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Track other = (Track) obj;
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		if (albumArtist == null) {
			if (other.albumArtist != null)
				return false;
		} else if (!albumArtist.equals(other.albumArtist))
			return false;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (discCount == null) {
			if (other.discCount != null)
				return false;
		} else if (!discCount.equals(other.discCount))
			return false;
		if (discNumber == null) {
			if (other.discNumber != null)
				return false;
		} else if (!discNumber.equals(other.discNumber))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (totalTime == null) {
			if (other.totalTime != null)
				return false;
		} else if (!totalTime.equals(other.totalTime))
			return false;
		if (trackCount == null) {
			if (other.trackCount != null)
				return false;
		} else if (!trackCount.equals(other.trackCount))
			return false;
		if (trackId == null) {
			if (other.trackId != null)
				return false;
		} else if (!trackId.equals(other.trackId))
			return false;
		if (trackNumber == null) {
			if (other.trackNumber != null)
				return false;
		} else if (!trackNumber.equals(other.trackNumber))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	
}
