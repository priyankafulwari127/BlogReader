import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Blog(
    val id: Long,
    val date: String,
    @SerialName("date_gmt")
    val dateGmt: String,
    val guid: Guid,
    val modified: String,
    @SerialName("modified_gmt")
    val modifiedGmt: String,
    val slug: String,
    val status: String,
    val type: String,
    val link: String,
    val title: Title,
    val content: Content,
    val excerpt: Excerpt,
    val author: Long,
    @SerialName("featured_media")
    val featuredMedia: Long,
    @SerialName("comment_status")
    val commentStatus: String,
    @SerialName("ping_status")
    val pingStatus: String,
    val sticky: Boolean,
    val template: String,
    val format: String,
    val meta: Meta,
    val categories: List<Long>,
    val tags: List<Long>,
    @SerialName("class_list")
    val classList: List<String>,
    @SerialName("jetpack_publicize_connections")
    val jetpackPublicizeConnections: List<JsonElement?>,
    @SerialName("aioseo_notices")
    val aioseoNotices: List<JsonElement?>,
    @SerialName("jetpack_featured_media_url")
    val jetpackFeaturedMediaUrl: String,
    @SerialName("jetpack_likes_enabled")
    val jetpackLikesEnabled: Boolean,
    @SerialName("jetpack_shortlink")
    val jetpackShortlink: String,
    @SerialName("jetpack-related-posts")
    val jetpackRelatedPosts: List<JsonElement?>,
    @SerialName("jetpack_sharing_enabled")
    val jetpackSharingEnabled: Boolean,
    @SerialName("_links")
    val links: Links,
)

@Serializable
data class Guid(
    val rendered: String,
)

@Serializable
data class Title(
    val rendered: String,
)

@Serializable
data class Content(
    val rendered: String,
    val protected: Boolean,
)

@Serializable
data class Excerpt(
    val rendered: String,
    val protected: Boolean,
)

@Serializable
data class Meta(
    @SerialName("jetpack_post_was_ever_published")
    val jetpackPostWasEverPublished: Boolean,
    @SerialName("_jetpack_newsletter_access")
    val jetpackNewsletterAccess: String,
    @SerialName("_jetpack_dont_email_post_to_subs")
    val jetpackDontEmailPostToSubs: Boolean,
    @SerialName("_jetpack_newsletter_tier_id")
    val jetpackNewsletterTierId: Long,
    @SerialName("_jetpack_memberships_contains_paywalled_content")
    val jetpackMembershipsContainsPaywalledContent: Boolean,
    @SerialName("_jetpack_memberships_contains_paid_content")
    val jetpackMembershipsContainsPaidContent: Boolean,
    val footnotes: String,
    @SerialName("jetpack_publicize_message")
    val jetpackPublicizeMessage: String,
    @SerialName("jetpack_publicize_feature_enabled")
    val jetpackPublicizeFeatureEnabled: Boolean,
    @SerialName("jetpack_social_post_already_shared")
    val jetpackSocialPostAlreadyShared: Boolean,
    @SerialName("jetpack_social_options")
    val jetpackSocialOptions: JetpackSocialOptions,
)

@Serializable
data class JetpackSocialOptions(
    @SerialName("image_generator_settings")
    val imageGeneratorSettings: ImageGeneratorSettings,
    val version: Long,
)

@Serializable
data class ImageGeneratorSettings(
    val template: String,
    val enabled: Boolean,
)

@Serializable
data class Links(
    val self: List<Self>,
    val collection: List<Collection>,
    val about: List<About>,
    val author: List<Author>,
    val replies: List<Reply>,
    @SerialName("version-history")
    val versionHistory: List<VersionHistory>,
    @SerialName("predecessor-version")
    val predecessorVersion: List<PredecessorVersion>,
    @SerialName("wp:featuredmedia")
    val wpFeaturedmedia: List<Featuredmedum>,
    @SerialName("wp:attachment")
    val wpAttachment: List<WpAttachment>,
    @SerialName("wp:term")
    val wpTerm: List<WpTerm>,
    val curies: List<Cury>,
)

@Serializable
data class Self(
    val href: String,
    val targetHints: TargetHints,
)

@Serializable
data class TargetHints(
    val allow: List<String>,
)

@Serializable
data class Collection(
    val href: String,
)

@Serializable
data class About(
    val href: String,
)

@Serializable
data class Author(
    val embeddable: Boolean,
    val href: String,
)

@Serializable
data class Reply(
    val embeddable: Boolean,
    val href: String,
)

@Serializable
data class VersionHistory(
    val count: Long,
    val href: String,
)

@Serializable
data class PredecessorVersion(
    val id: Long,
    val href: String,
)

@Serializable
data class Featuredmedum(
    val embeddable: Boolean,
    val href: String,
)

@Serializable
data class WpAttachment(
    val href: String,
)

@Serializable
data class WpTerm(
    val taxonomy: String,
    val embeddable: Boolean,
    val href: String,
)

@Serializable
data class Cury(
    val name: String,
    val href: String,
    val templated: Boolean,
)
