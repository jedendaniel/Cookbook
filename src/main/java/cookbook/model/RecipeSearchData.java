package cookbook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RecipeSearchData {
    private String name;
    private String author;
    private List<String> requiredIngredients = new ArrayList<>();
    private List<String> optionalIngredients = new ArrayList<>();
    private Builder builder;

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getRequiredIngredients() {
        return requiredIngredients;
    }

    public List<String> getOptionalIngredients() {
        return optionalIngredients;
    }

    public Builder toBuilder() {
        return getBuilder()
                .setName(name)
                .setAuthor(author)
                .setRequiredIngredients(requiredIngredients)
                .setOptionalIngredients(optionalIngredients);
    }

    public Recipe toRecipe() {
        Recipe recipe = new Recipe(name, author);
        List<RecipeIngredient> recipeIngredients = Optional.ofNullable(requiredIngredients)
                .map(ingredients -> ingredients.stream()
                        .map(ingredient -> new RecipeIngredient(recipe, new Ingredient(ingredient), null))
                        .collect(Collectors.toList()))
                .orElse(null);
        recipe.setIngredients(recipeIngredients);
        return recipe;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private RecipeSearchData recipe = new RecipeSearchData();

        public RecipeSearchData.Builder setName(String name) {
            recipe.name = name;
            return this;
        }

        public String getName() {
            return recipe.name;
        }

        public RecipeSearchData.Builder setAuthor(String author) {
            recipe.author = author;
            return this;
        }

        public String getAuthor() {
            return recipe.author;
        }

        public RecipeSearchData.Builder setRequiredIngredients(List<String> ingredients) {
            recipe.requiredIngredients = ingredients != null ? new ArrayList<>(ingredients) : List.of();
            return this;
        }

        public RecipeSearchData.Builder setOptionalIngredients(List<String> ingredients) {
            recipe.optionalIngredients = ingredients != null ? new ArrayList<>(ingredients) : List.of();
            return this;
        }

        public List<String> getRequiredIngredients() {
            return recipe.requiredIngredients;
        }

        public List<String> getOptionalIngredients() {
            return recipe.optionalIngredients;
        }

        public RecipeSearchData build() {
            return recipe;
        }
    }
}
