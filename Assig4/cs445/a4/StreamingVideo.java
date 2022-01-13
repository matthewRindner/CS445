package cs445.a4;

import java.util.NoSuchElementException;
import java.util.*;

/**
 * This abstract data type represents the backend for a streaming video service.
 * It stores the videos (TV episodes and films), TV series, and users in the
 * system, as well as the ratings that users assign to videos.
 */
public interface StreamingVideo {

    /**
     * <p>The abstract methods below are declared as void methods with no
     * parameters. You need to expand each declaration to specify a return type
     * and parameters, as necessary. You also need to include a detailed comment
     * for each abstract method describing its effect, its return value, any
     * corner cases that the client may need to consider, any exceptions the
     * method may throw (including a description of the circumstances under
     * which this will happen), and so on. You should include enough details
     * that a client could use this data structure without ever being surprised
     * or not knowing what will happen, even though they haven't read the
     * implementation. </p>
     */

    /**
     * <p>Adds a new video to the collection if newVideo is not null, there is room in the collection, 
     * and if newVideo does not already exist in the collection without altering any other videos. 
     * Method will return true if the add operation is successful, otherwise it will throw setFullException 
     * if the collection is full. If newVideo is null, addVideo will throw a nullPointerException 
     * without changing the collection. If the newVideo is already in the collection it will return false.</p>
     *
     * @param newVideo the video to be added to collection
     * @return true if the add operation is successfull, false if the video already 
     * exists in the collection
     * @throws NullPointerException if newVideo is null
     * @throws SetFullException if the collection is full and cannot add a video
     */
    public boolean addVideo(Video newVideo) 
        throws NullPointerException, SetFullException;
         

    /**
     * Removes an existing video from the system if its in the collection without changing other videos
     * in the collection. Method returns true if the remove operation is successful, false if otherwise. 
     * If the video is not in the collection, removeVideo throws an illegalArgument Exception.
     * If videoElem is null, remmoveVideo throws nullPointerException
     * @param videoElem  the video to be removed from the collection
     * @return true if the remove operation is successfull, false if the video is not in the collection.
     * @throws IllegalArgumentException if videoELem is not in the collection
     * @throws NullPointerException if videoElem in null
     */
    public boolean removeVideo(Video videoElem)
        throws IllegalArgumentException, NullPointerException;

    /**
     * Adds an existing television episode to an existing television series if newTvEpisode and the TV series
     * is not null, there is room in the collection, and if newTvEpisode does not already exist in the collection
     * without altering any other videos. Method will return true if the add operation is successful, otherwise 
     * it will throw a setFullException if the collection is full. If newTvEpisode or if the TV series is null, 
     * addToSeries will throw a nullPointerException without changing the collection. If the newTvEpisode is 
     * already in the collection it will return false
     *
     * @param newTvEpisode the TvEpisode to be added to collection
     * @param tvSeriesElem the TV series to which the newTvEpisode will be added 
     * @return true if the add operation is successfull, false if the TvEpisode already 
     * exists in the collection or in the TvSeries
     * @throws NullPointerException if newTvEpisode or if tvSeriesElem is null
     * @throws SetFullException if the collection is full and cannot add a TvEpisode
     */
    public boolean addToSeries(TvEpisode newTvEpisode, TvSeries tvSeriesElem)
        throws NullPointerException, SetFullException;

    /**
     * Removes a television episode from an exisiting television series if both the TvSeries and TvEpisode are
     * not null without changing other elements in the collection. Method will throw NullPointerException if 
     * either paramater is null. If the TvSeries is not in the system, method will throw an IllegalArgumentException.
     * If the TvEpisode is not in the given TvSeries, method throws IllegalArgumentException.
     *
     * @param tvEpisodeElem the TvEpisode to be removed from the collection
     * @param tvSeriesElem the TV series to which the TvEpisode will be removed 
     * @throws NullPointerException if the tvEpisodeElem or tvSeriesElem is null
     * @throws IllegalArgumentException if tvEpisodeElem or tvSeriesElem is not in the collection
     */
    public void removeFromSeries(TvEpisode tvEpisodeElem, TvSeries tvSeriesElem)
        throws NullPointerException, IllegalArgumentException;

    /**
     * Sets a user's rating for a video, as a number of stars from 1 to 5 if no rating already exists.
     * If a rating does exists for the video and is different than the origianl rating, the old rating
     * is replaced with the new rating. If either the User or the Video is null, method throws 
     * NullPointerException. 
     *
     * @param userElem an exisiting user that rates a video
     * @param videoElem the video given a rating
     * @param rating the int value representing the star rating from 1 to 5
     * @throws IllegalArgumentException if the rating is outside the range of 1 to 5
     *      and if either the user or video is not in the collection
     * @throws NullPointerException if the user or the video is null
     */
    public void rateVideo(User userElem, Video videoElem, int rating)
        throws NullPointerException, IllegalArgumentException;

    /**
     * Clears a user's rating on a video. If this user has rated this video and
     * the rating has not already been cleared, then the rating is cleared and
     * the state will appear as if the rating was never made. If there is no
     * such rating on record (either because this user has not rated this video,
     * or because the rating has already been cleared), then this method will
     * throw an IllegalArgumentException. If the user or video is not in the
     * system, then this method will throw NoSuchElementException.
     *
     * @param theUser user whose rating should be cleared
     * @param theVideo video from which the user's rating should be cleared
     * @throws IllegalArgumentException if the user does not currently have a
     * rating on record for the video
     * @throws NoSuchElementException if either the user or the video is not in
     * the system
     * @throws NullPointerException if either the user or the video is null
     */
    public void clearRating(User theUser, Video theVideo)
    throws IllegalArgumentException,
           NullPointerException,
           NoSuchElementException;

    /**
     * Predicts the rating a user will assign to a video that they have not yet
     * rated, as a number of stars from 1 to 5 as a double number. If the video already has a rating
     * the method throws an IllegalArgumentException. If the User or Video is null,
     * the method will throw a NullPointerException. If neither the user or the video 
     * is in the collection, method will throw an IllegalArgumentException. The method will predict
     * the rating of a valid video based in the users previous rated videos. If no videos are rated
     * by the user, the method returns null.
     *
     * @param userElem the user for which the predicted rate will be made
     * @param videoElem the video to which the method will predict the rating 
     * @return the predicted rating of the video as a double
     * @throws NullPointerException if either the user or video are null
     * @throws IllegalArgumentException if the user or video are not in the collection 
     *  or if the video already has a rating
     */
    public double predictRating(User userElem, Video videoElem)
        throws NullPointerException, IllegalArgumentException;

    /**
     * Returns a video for a user that they are predicted to like if the video and 
     * user is valid. If the user is null, method throws a NullPointerException. 
     * If the user is not in the collection, method throws an IllegalArgumentException.
     * 
     *
     * @param userElem the user for which the video is sugegested
     * @return the suggested video
     * @throws NullPointerException if the user is null
     * @throws IllegalArgumentException if the user is not in the collection 
     *  or if the suggested video is not in the collection
     */
    public Video suggestVideo(User userElem)
        throws NullPointerException, IllegalArgumentException;


}

