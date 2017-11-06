/*
 * SonarQube PHP Plugin
 * Copyright (C) 2010-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.php.checks;

import org.sonar.check.Rule;
import org.sonar.php.checks.utils.Equality;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.expression.AssignmentExpressionTree;
import org.sonar.plugins.php.api.tree.expression.ExpressionTree;
import org.sonar.plugins.php.api.tree.expression.UnaryExpressionTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

@Rule(key = UselessIncrementCheck.KEY)
public class UselessIncrementCheck extends PHPVisitorCheck {

  public static final String KEY = "S2123";

  @Override
  public void visitAssignmentExpression(AssignmentExpressionTree tree) {
    if (tree.is(Tree.Kind.ASSIGNMENT)) {
      ExpressionTree value = tree.value();
      if (value.is(Tree.Kind.POSTFIX_INCREMENT, Tree.Kind.POSTFIX_DECREMENT) && Equality.areSyntacticallyEquivalent(tree.variable(), ((UnaryExpressionTree) value).expression())) {
        String message = String.format("Remove this %s or correct the code not to waste it.", value.is(Tree.Kind.POSTFIX_INCREMENT) ? "increment" : "decrement");
        context().newIssue(this, value, message);
      }
    }
    super.visitAssignmentExpression(tree);
  }

}
