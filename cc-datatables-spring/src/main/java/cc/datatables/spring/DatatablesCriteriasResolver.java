package cc.datatables.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import cc.datatables.core.annotation.DataTableAttribute;
import cc.datatables.utils.DatatablesCriteriaUtils;

public class DatatablesCriteriasResolver implements WebArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		DataTableAttribute dataTableAttribute = methodParameter.getParameterAnnotation(DataTableAttribute.class);
		if (dataTableAttribute != null) {
			HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
			return DatatablesCriteriaUtils.getFromRequest(request);
		}
		return WebArgumentResolver.UNRESOLVED;
	}

}
