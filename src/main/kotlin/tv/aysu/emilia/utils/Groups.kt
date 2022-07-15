package tv.aysu.emilia.utils

object Groups {

    /**
     * Gets a List with the Sr Team groups
     *
     * @return all ids of the sr Team groups
     */
    @JvmStatic
    val srTeam: List<String> = mutableListOf()

    /**
     * Gets a List with the moderation Team groups
     *
     * @return all ids of the moderation Team groups
     */
    @JvmStatic
    val modTeam: List<String> = mutableListOf()

    /**
     * Gets a List with the supporter Team groups
     *
     * @return all ids of the supporter Team groups
     */
    @JvmStatic
    val supTeam: List<String> = mutableListOf()

    /**
     * Gets a List with the Team groups
     *
     * @return all ids of the Team groups
     */
    @JvmStatic
    val team: List<String> = mutableListOf()

    /**
     * Checks if the given group is a sr Team group
     *
     * @param id of the group which should be checked
     * @return true if the sr team groups contains the group
     */
    @JvmStatic
    fun isSrTeamGroup(id: String) : Boolean {
        return srTeam.contains(id)
    }

    /**
     * Checks if the given group is a sr Team group
     *
     * @param id of the group which should be checked
     * @return true if the sr team groups contains the group
     */
    @JvmStatic
    fun isModerationTeamGroup(id: String) : Boolean {
        return srTeam.contains(id)
    }

}