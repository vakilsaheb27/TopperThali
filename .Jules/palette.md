## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standardized Empty States for List Views
**Learning:** A blank screen when no data is present can be confusing. Providing a centered empty state with an icon and clear CTA (Call to Action) improves user orientation.
**Action:** Implement a centered `LinearLayout` with an emoji and instructional text, toggled by data presence, to guide users in empty list views.
