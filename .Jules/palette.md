## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-22 - Helpful Empty States and Accessible Navigation
**Learning:** A blank screen when data is absent (e.g., no students) creates user uncertainty. Providing a visual empty state with a clear call-to-action improves the onboarding experience. Additionally, consistent back navigation in secondary activities using theme-aware attributes ensures both accessibility and UI coherence.
**Action:** Implement informative empty states for all list-based views and ensure all Toolbars have accessible back navigation using `?attr/homeAsUpIndicator` and proper `contentDescription`.
